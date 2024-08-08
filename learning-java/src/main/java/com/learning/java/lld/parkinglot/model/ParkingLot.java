package com.learning.java.lld.parkinglot.model;

import com.learning.java.lld.parkinglot.exception.SlotValidationException;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class is responsible to manage parkinglot and allocate parking slot
 */
public class ParkingLot {

    int capacity = 0;
    Map<Integer, Slot> slotMap;

    public ParkingLot(int capacity) {
        slotMap = new HashMap<>();
        this.capacity = capacity;
        //Create empty slot
        this.slotMap = addEmptySlot(0, capacity);
    }

    private Map<Integer, Slot> addEmptySlot(int currentCapacity, int capacity) {
        Map<Integer, Slot> map = new HashMap<>(capacity);
        for(int i=1 ;i<=capacity;i++) {
            map.put(i + currentCapacity, new Slot(i+currentCapacity));
            capacity--;
        }
        return map;

    }

    public void park(Integer slotNumber, Vehicle vehicle) throws SlotValidationException {
        Optional<Slot> slot = getSlot(slotNumber);
        if(slot.isPresent()) {
            slot.get().park(vehicle);
        } else {
            throw new SlotValidationException("Slot is not available to park");
        }
    }

    public void makeSlotAvailable(Integer slotNumber) throws SlotValidationException {
        Optional<Slot> slot = getSlot(slotNumber);
        if(slot.isPresent()) {
            slot.get().unPark();
        } else {
            throw new SlotValidationException("Slot is not available to make it available");
        }
    }

    public Optional<Slot> getSlot(Integer slotNumber) {
        // First of all you should not use this variable directly, put it under method
       return Optional.ofNullable(slotMap.getOrDefault(slotNumber, null));
    }

    public Map<Integer, Slot> getSlotMap() {
        return slotMap;
    }
}
