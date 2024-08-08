package com.learning.java.lld.socialnetwork.service;

import com.learning.java.lld.socialnetwork.model.Comment;
import com.learning.java.lld.socialnetwork.model.Notification;
import com.learning.java.lld.socialnetwork.model.Post;
import com.learning.java.lld.socialnetwork.model.User;
import com.learning.java.lld.socialnetwork.model.enums.NotificationType;

import java.util.List;

public interface SocialNetworkService {
    User registerUser(User user);

    User loginUser(User user);

    void sendFriendRequest(String senderId, String receiverId);

    void acceptFriendRequest(String acceptorId, String senderId);

    void createPost(String userId, Post post);

    void likePost(String likedByUserId, String postId);

    void commentOnPost(String postId, Comment comment);

    List<Notification> readNotification(String userId
            , List<NotificationType> notificationTypes);

    void viewAllPostByUser(String userId);

    void viewNotification(List<Notification> notifications);
}
