package com.learning.java.lld.subscription_Atlassian.model;

import lombok.Data;

@Data
public class Product {
    private String name;
    private String productName;
    private CustomerSubscription customerSubscription;
}
