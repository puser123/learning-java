package com.learning.java.lld.onlineshopping.domain;

public class CartItem {
    private Item item;
    private int count;

    public CartItem() {}

    public void setCount(int count) {
        this.count = count;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public Item getItem() {
        return item;
    }
}
