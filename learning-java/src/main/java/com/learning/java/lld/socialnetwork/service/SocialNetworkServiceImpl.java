package com.learning.java.lld.socialnetwork.service;

import com.learning.java.lld.socialnetwork.model.Comment;
import com.learning.java.lld.socialnetwork.model.Notification;
import com.learning.java.lld.socialnetwork.model.Post;
import com.learning.java.lld.socialnetwork.model.User;
import com.learning.java.lld.socialnetwork.model.enums.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SocialNetworkServiceImpl implements SocialNetworkService{

    private static SocialNetworkService socialNetworkService;
    private static final Logger log = LoggerFactory.getLogger(SocialNetworkServiceImpl.class);
    Map<String, User> userMap;
    Map<String, Post> postMap;
    Map<String, List<Notification>> notificationMap;

    public SocialNetworkServiceImpl() {
        this.userMap = new ConcurrentHashMap<>();
        this.postMap = new ConcurrentHashMap<>();
        this.notificationMap = new ConcurrentHashMap<>();
    }

    public static SocialNetworkService getInstance() {
        if(socialNetworkService == null) {
            socialNetworkService = new SocialNetworkServiceImpl();
        }
        return socialNetworkService;
    }
    @Override
    public User registerUser(User user) {
        this.userMap.put(user.getId(), user);
        log.info("User registered :{}", user.getName());
        return user;
    }

    @Override
    public User loginUser(User user) {
        User existingUser = userMap.getOrDefault(user.getId(), null);

        if(existingUser != null &&
        existingUser.getEmail().equalsIgnoreCase(user.getEmail())
            && existingUser.getPassword().equalsIgnoreCase(user.getPassword())) {
            return existingUser;
        }
        log.info("Logged in user :{}", user.getName());
        return null;
    }

    @Override
    public void sendFriendRequest(String senderId, String receiverId) {
        User sender = userMap.getOrDefault(senderId, null);
        User receiver = userMap.getOrDefault(receiverId, null);
        log.info("Friend request sent");
        if(Objects.nonNull(sender) && Objects.nonNull(receiver)) {
            addNotifications(sender.getId(),Arrays.asList( receiver.getId()),"friend request sent", NotificationType.FRIEND_REQUEST_RECEIVED);
        }
    }

    private void addNotifications(String senderId, List<String> receiverIds ,String description, NotificationType notificationType) {

       receiverIds
                .stream()
                .map(recieverId -> {
                    Notification notification = new Notification(UUID.randomUUID()
                            .toString(),
                            senderId, recieverId, description, notificationType);
                    return notification;
                }).forEach(notification -> {
                   List<Notification> existingNotifications = notificationMap.get(notification.getToUserId());
                   if(existingNotifications == null || existingNotifications.isEmpty()) {
                       notificationMap.put(notification.getToUserId(), new ArrayList<>());
                   }
                   notificationMap.get(notification.getToUserId()).add(notification);
               });
    }

    @Override
    public void acceptFriendRequest(String acceptorId, String senderId) {
        List<Notification> notifications = notificationMap.get(acceptorId)
                .stream().filter(notification -> notification.getType().equals(NotificationType.FRIEND_REQUEST_RECEIVED))
                .collect(Collectors.toList());
        if(notifications != null && !notifications.isEmpty()) {
            // Add user to its friend request,
            User acceptor = userMap.get(acceptorId);
            User sender  = userMap.get(senderId);
            acceptor.getFriends().add(senderId);
            sender.getFriends().add(acceptorId);
            removeNotification(acceptorId, notifications);
            log.info("Friend requested Accepted by user : {}, {}", acceptor.getName(),
                    acceptor.getName(),
                    sender.getName()) ;
        } else {
            log.info("No new notification received");
        }
    }


    @Override
    public void createPost(String userId, Post post) {
        User user = userMap.getOrDefault(userId, null);
        if(Objects.isNull(user)) {
            log.info("User does not exists");
        }
        user.addPost(post);
        postMap.put(post.getId(), post);
        List<String> friendsId = user.getFriends();
        addNotifications(userId, friendsId, "Post created", NotificationType.POST_CREATED);
    }

    @Override
    public void likePost(String likedByUserId, String postId) {
        // update post in post map
        Post post = postMap.get(postId);
        post.addLike(likedByUserId);
        // update post in user post data.
        User postOwner = userMap.get(post.getOwnerId());
        postOwner.getPosts()
                .get(postId)
                .addLike(likedByUserId);
        addNotifications(likedByUserId,Arrays.asList( postOwner.getId()), "Liked your post", NotificationType.LIKED);
    }

    @Override
    public void commentOnPost(String postId, Comment comment) {
        Post post = postMap.get(postId);
        post.addComment(comment);
        User postOwner = userMap.get(post.getOwnerId());
        postOwner.getPosts()
                .get(postId)
                .addComment(comment);
        addNotifications(comment.getByUserId(), Arrays.asList(postOwner.getId()), "commented on post", NotificationType.COMMENT_CREATED);
    }

    @Override
    public List<Notification> readNotification(String userId
            , List<NotificationType> notificationTypes) {
        List<Notification> notifications =  notificationMap.get(userId)
                .stream()
                .filter(notification -> notificationTypes.contains(notification.getType()))
                .collect(Collectors.toList());
        removeNotification(userId, notifications);
        return notifications;
    }

    private void removeNotification(String acceptorId, List<Notification> notifications) {
        notifications
                .stream()
                .forEach(notification -> {
                    notificationMap.get(acceptorId)
                            .removeIf(res -> res.getId().equals(notification.getId()));
                });
    }

    @Override
    public void viewAllPostByUser(String userId) {
        Optional<User> user = Optional.ofNullable(userMap.getOrDefault(userId, null));
        if(user.isPresent()) {
            log.info("Post by user name: {} and posts: {}", user.get().getName(),
                    user.get().getPosts()
                            .entrySet()
                            .stream().map(stringPostEntry -> stringPostEntry.getValue()
                                    .getTitle())
                            .collect(Collectors.joining(", ")));
        } else{
            log.info("User is not present");
        }
    }

    @Override
    public void viewNotification(List<Notification> notifications) {
        if(notifications == null || notifications.size() == 0) {
            log.info("No notification to read");
        }
        notifications.stream()
                .forEach(notification -> {
                    log.info("Notification by userId {}, from userId :{} , notification description :{}",
                            notification.getToUserId(), notification.getByUserId(),
                            notification.getDescription());
                });
    }

}
