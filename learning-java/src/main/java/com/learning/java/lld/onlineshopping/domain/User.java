package com.learning.java.lld.onlineshopping.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private List<Order> orderList;

    public User(UUID userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email= email;
        this.password = password;
        this.orderList = new ArrayList<>(5);
    }

    public UUID getUserId() {
        return userId;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public String getName() {
        return name;
    }
}
