package com.learning.java.lld.onlineshopping.domain;

public class OrderDetail {
    private Item item;
    private int count;

    public void setItem(Item item) {
        this.item = item;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Item getItem() {
        return item;
    }
}