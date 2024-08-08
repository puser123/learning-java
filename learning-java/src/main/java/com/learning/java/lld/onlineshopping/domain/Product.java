package com.learning.java.lld.onlineshopping.domain;

import java.util.UUID;

public class Product {
    UUID productId;
    Item item;
    int count;

    public  Product(UUID productId, Item item, int count) {
        this.productId = productId;
        this.item = item;
        this.count = count;
    }

    public UUID getProductId() {
        return productId;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }
    public void updateCount(int value) {
        this.count += value;
    }

}
