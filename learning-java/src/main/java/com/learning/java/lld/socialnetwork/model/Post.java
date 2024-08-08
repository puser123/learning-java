package com.learning.java.lld.socialnetwork.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String id;
    private String ownerId;
    private String title;
    private String description;
    private List<String> imageUrls;
    private List<String> videoUrls;
    private List<String> likedUserIds;
    private List<Comment> comments;

    public Post(String id, String title, String description, String ownerId) {
        this.id = id;
        this.title = title;
        this.ownerId = ownerId;
        this.description= description;
        this.likedUserIds=new ArrayList<>();
        this.imageUrls = new ArrayList<>();
        this.videoUrls = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addLike(String userId) {
        this.likedUserIds.add(userId);
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
