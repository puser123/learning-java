package com.learning.java.lld.parkinglot.model;

public class Car implements Vehicle{
    private String regNumber;
    private String color;

    public Car(String regNumber, String color) {
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


    public static class CarBuilder {
        private String regNumber;
        private String color;

        CarBuilder regNumber(String regNumber) {
            this.regNumber = regNumber;
            return this;
        }

        CarBuilder color(String color) {
            this.color = color;
            return this;
        }

        Car build() {
            Car car = new Car(this.regNumber, this.color);
            return car;
        }
    }
}
