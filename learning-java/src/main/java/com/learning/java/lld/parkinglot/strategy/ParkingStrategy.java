package com.learning.java.lld.parkinglot.strategy;

import java.util.List;

/*

 */
public interface ParkingStrategy {

    void addSlot(Integer slotNumber);

    void addSlot(List<Integer> slotNumber);

    void removeSlot(Integer slotNumber);

    Integer getNextSlot() throws Exception;

}
