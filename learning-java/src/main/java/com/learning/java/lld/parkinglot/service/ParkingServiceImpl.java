package com.learning.java.lld.parkinglot.service;

import com.learning.java.lld.parkinglot.exception.SlotValidationException;
import com.learning.java.lld.parkinglot.model.ParkingLot;
import com.learning.java.lld.parkinglot.model.Slot;
import com.learning.java.lld.parkinglot.model.Vehicle;
import com.learning.java.lld.parkinglot.strategy.NaturalNumberOrderingParkingSlot;
import com.learning.java.lld.parkinglot.strategy.ParkingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ParkingServiceImpl implements ParkingService{

    private ParkingStrategy parkingStrategy;
    private ParkingLot parkingLot;

    public ParkingServiceImpl() {
    }

    @Override
    public void createParkingLot(int initialCapacity) {

        if(this.parkingLot == null) {
            parkingLot = new ParkingLot(initialCapacity);
            parkingStrategy = new NaturalNumberOrderingParkingSlot();
            // add all the available slot to the parkingStrategy
            List<Integer> availableSlot = this.parkingLot.getSlotMap()
                    .keySet().stream().collect(Collectors.toList());
            this.parkingStrategy.addSlot(availableSlot);
        }
    }

    @Override
    public Map<Integer, Slot> getOccupiedSlots() {
        Deque<String> list = new LinkedList<>();
        String s = "hello";
        String result = "";
        result += s.charAt(0);

        return this.parkingLot.getSlotMap()
                .entrySet()
                .stream().filter(integerSlotEntry -> integerSlotEntry.getValue().isAvailable())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Integer park(Vehicle vehicle) throws Exception {
        Integer slotNumber = this.parkingStrategy.getNextSlot();
        this.parkingLot.park(slotNumber, vehicle);
        this.parkingStrategy.removeSlot(slotNumber);
        return slotNumber;
    }

    @Override
    public Integer unPark(Integer slotNumber) throws SlotValidationException {
        this.parkingLot.makeSlotAvailable(slotNumber);
        this.parkingStrategy.addSlot(slotNumber);
        return slotNumber;
    }

    @Override
    public boolean isAvailable(Integer slotNumber) {
        return this.parkingLot.getSlot(slotNumber)
                .map(slot -> slot.isAvailable())
                .orElse(Boolean.FALSE);
    }

    @Override
    public void getParkingStatus() {
        this.parkingLot.getSlotMap()
                .entrySet()
                .forEach(integerSlotEntry ->  {
                    log.info("Slot Number : {}, car Number :{} ", integerSlotEntry.getKey(),
                            !integerSlotEntry.getValue().isAvailable() ?
                            integerSlotEntry.getValue().getVehicle().getVehicleNumber() : "Nothing Parked");
                });
    }

    @Override
    public boolean isReady() {
        return this.parkingLot != null;
    }
}
