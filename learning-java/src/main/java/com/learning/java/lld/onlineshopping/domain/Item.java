package com.learning.java.lld.onlineshopping.domain;

import java.util.UUID;

public class Item {
    private UUID itemId;
    private String name;
    private String description;
    private double price;

    public Item(UUID itemId, String name, String description, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description =description;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public UUID getItemId() {
        return itemId;
    }
}
