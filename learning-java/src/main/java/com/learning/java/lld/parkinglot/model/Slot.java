package com.learning.java.lld.parkinglot.model;

import java.util.Objects;

public class Slot {
    private Integer slotNumber;

    Vehicle vehicle;

    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }
    public Integer park(Vehicle vehicle) {
        if(Objects.nonNull(vehicle)) {
            this.vehicle = vehicle;
        }
        return slotNumber;
    }

    public Vehicle unPark() {
        Vehicle currentVehicle = vehicle;
        vehicle = null;
        return currentVehicle;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
