package com.learning.java.lld.parkinglot.strategy;

import java.util.List;
import java.util.TreeSet;

public class NaturalNumberOrderingParkingSlot implements ParkingStrategy{

    private TreeSet<Integer> availableSlots;

    public NaturalNumberOrderingParkingSlot() {
        this.availableSlots = new TreeSet<>();
    }

    @Override
    public void addSlot(Integer slotNumber) {
        availableSlots.add(slotNumber);
    }

    @Override
    public void addSlot(List<Integer> slotNumber) {
        availableSlots.addAll(slotNumber);
    }


    @Override
    public void removeSlot(Integer slotNumber) {
        if(availableSlots.contains(slotNumber)) {
            availableSlots.remove(slotNumber);
        }
    }

    @Override
    public Integer getNextSlot() throws Exception {
        if(availableSlots.isEmpty()) {
            throw new Exception("No slot is available currently");
        }
        return availableSlots.first();
    }
}
