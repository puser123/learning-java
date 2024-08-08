package com.learning.java.lld.socialnetwork.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<String> friends;
    private Map<String, Post> posts;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.friends = new ArrayList<>();
        this.posts = new HashMap<>();
    }

    public void addPost(Post post) {
        this.posts.put(post.getId(), post);
    }

    public void addFriend(User user) {
        this.friends.add(user.getId());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFriends() {
        return friends;
    }

    public Map<String, Post> getPosts() {
        return posts;
    }
}
