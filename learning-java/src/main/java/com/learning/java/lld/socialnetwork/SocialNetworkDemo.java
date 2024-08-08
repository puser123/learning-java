package com.learning.java.lld.socialnetwork;

import com.learning.java.lld.socialnetwork.model.Comment;
import com.learning.java.lld.socialnetwork.model.Notification;
import com.learning.java.lld.socialnetwork.model.Post;
import com.learning.java.lld.socialnetwork.model.User;
import com.learning.java.lld.socialnetwork.model.enums.NotificationType;
import com.learning.java.lld.socialnetwork.service.SocialNetworkService;
import com.learning.java.lld.socialnetwork.service.SocialNetworkServiceImpl;

import java.util.*;

public class SocialNetworkDemo {

    public static void main(String[] args) {
        SocialNetworkService socialNetworkService = SocialNetworkServiceImpl.getInstance();

        User sender = new User(UUID.randomUUID().toString(),
                "Pankaj", "pk@gmail.com", "pks");
        User receiver = new User(UUID.randomUUID().toString(),
                "Ranjeet", "pks@gmail.com", "pks");
        socialNetworkService.registerUser(sender);
        socialNetworkService.loginUser(sender);

        socialNetworkService.registerUser(receiver);
        socialNetworkService.loginUser(receiver);

        socialNetworkService.sendFriendRequest(sender.getId(), receiver.getId());
        socialNetworkService.readNotification(receiver.getId(), Arrays.asList(NotificationType
                .POST_CREATED, NotificationType.COMMENT_CREATED));
        socialNetworkService.acceptFriendRequest(receiver.getId(), sender.getId());

        Post post1 = new Post("online assesment", "online assement", "description",
                sender.getId());
        socialNetworkService.createPost(sender.getId(), post1);
        List<Notification> notificationList = socialNetworkService.readNotification(receiver.getId(), Arrays.asList(NotificationType
                .POST_CREATED, NotificationType.COMMENT_CREATED));
        socialNetworkService.viewNotification(notificationList);

        socialNetworkService.viewAllPostByUser(sender.getId());
        notificationList = socialNetworkService.readNotification(receiver.getId(), Arrays.asList(NotificationType
                .POST_CREATED, NotificationType.COMMENT_CREATED));
        socialNetworkService.viewNotification(notificationList);
        socialNetworkService.commentOnPost(post1.getId(), new Comment(UUID.randomUUID().toString(),
                sender.getId(), "this is helpful"));

        notificationList = socialNetworkService.readNotification(receiver.getId(), Arrays.asList(NotificationType
                .POST_CREATED, NotificationType.COMMENT_CREATED));
        socialNetworkService.viewNotification(notificationList);
        notificationList = socialNetworkService.readNotification(sender.getId(), Arrays.asList(NotificationType
                .POST_CREATED, NotificationType.COMMENT_CREATED));
        socialNetworkService.viewNotification(notificationList);


    }
}
