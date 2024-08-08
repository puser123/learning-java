package com.learning.java.lld.onlineshopping.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID cartId;
    private UUID userId;
    private List<CartItem> itemList;

    public Cart() {
        itemList = new ArrayList<>(18);
    }

    public List<CartItem> getItemList() {
        return itemList;
    }

    public UUID getCartId() {
        return cartId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setItemList(List<CartItem> itemList) {
        this.itemList = itemList;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }
}