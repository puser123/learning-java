package com.learning.java.lld.flipkartgymbooking.model;

import lombok.Data;

@Data
public class Address {
    private String addressLine;
    private String city;
    private String state;
    private double latitude;
    private double longitude;
    private String pinCode;
}
