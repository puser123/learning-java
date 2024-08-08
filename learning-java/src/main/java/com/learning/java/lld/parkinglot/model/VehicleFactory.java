package com.learning.java.lld.parkinglot.model;

public class VehicleFactory {

    public Vehicle getVehicle(String vehicleType, String regNumber, String color) {
        if("car".equals(vehicleType.toLowerCase())) {
            return new Car(regNumber, color);
        } else if("bike".equals(vehicleType.toLowerCase())) {
            return new Bike(regNumber, color);
        }
        return null;
    }
}
