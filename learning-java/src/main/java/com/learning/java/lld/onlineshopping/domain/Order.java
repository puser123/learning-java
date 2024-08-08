package com.learning.java.lld.onlineshopping.domain;

import java.util.List;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private UUID userId;
    private List<OrderDetail> orderDetailList;
    private OrderStatus orderStatus;

    public void setOrderDetailsList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
