package com.learning.java.lld.parkinglot.model;

public class Bike implements Vehicle{
    private String regNumber;
    private String color;

    public Bike(String regNumber, String color) {
        this.regNumber = regNumber;
        this.color = color;
    }
    @Override
    public String getVehicleNumber() {
        return this.regNumber;
    }

    @Override
    public String getColor() {
        return this.color;
    }

}