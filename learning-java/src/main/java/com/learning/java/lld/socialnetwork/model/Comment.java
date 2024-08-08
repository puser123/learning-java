package com.learning.java.lld.socialnetwork.model;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private String id;
    private String byUserId;
    private String description;
    private List<String> likedUserId;

    public Comment(String id,String byUserId, String description) {
        this.id = id;
        this.byUserId = byUserId;
        this.description = description;
        this.likedUserId = new ArrayList<>();
    }
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getLikedUserId() {
        return likedUserId;
    }

    public void likedByUser(String userId) {
        this.likedUserId.add(userId);
    }

    public String getByUserId() {
        return byUserId;
    }

}
