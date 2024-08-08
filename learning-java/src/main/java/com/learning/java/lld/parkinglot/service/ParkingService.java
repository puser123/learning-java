package com.learning.java.lld.parkinglot.service;

import com.learning.java.lld.parkinglot.exception.SlotValidationException;
import com.learning.java.lld.parkinglot.model.Slot;
import com.learning.java.lld.parkinglot.model.Vehicle;

import java.util.Map;

public interface ParkingService {
    void createParkingLot(int initialCapacity);

    Map<Integer, Slot> getOccupiedSlots();

    Integer park(Vehicle vehicle) throws Exception;

    Integer unPark(Integer slotNumber) throws SlotValidationException;

    boolean isAvailable(Integer slotNumber);

    void getParkingStatus();

    boolean isReady();
}
