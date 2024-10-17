package com.learning.java.lld.flipkartgymbooking;

import com.learning.java.lld.flipkartgymbooking.model.*;
import com.learning.java.lld.flipkartgymbooking.strategy.StrategyFactory;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class GymBookingMain {

    public static void main(String[] args) {
        StrategyFactory strategyFactory = new StrategyFactory();
        BookingService bookingService = new BookingService(strategyFactory);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Center center = new Center();
        center.setCenterId(UUID.randomUUID());
        center.setCenterName("Bellandur");
        center.setStatus(Status.ACTIVE);
        bookingService.addCenter(center);

        Slot slot = new Slot();
        slot.setSlotId(UUID.randomUUID());
        slot.setSeatCount(30);
        LocalTime startTime = LocalTime.parse("06:00:00",formatter);
        slot.setStartTime(startTime);
        slot.setDuration(1);
        slot.setSlotType(SlotType.WEIGHTS);
        slot.setDurationUnit(DurationUnit.HOUR);

        Slot slot1 = new Slot();
        slot1.setSlotId(UUID.randomUUID());
        slot1.setSeatCount(30);
        LocalTime startTime1 = LocalTime.parse("08:00:00",formatter);
        slot1.setStartTime(startTime1);
        slot1.setSlotType(SlotType.RUNNING);
        slot1.setDuration(1);
        slot1.setDurationUnit(DurationUnit.HOUR);

        Slot slot2 = new Slot();
        slot2.setSlotId(UUID.randomUUID());
        slot2.setSeatCount(30);
        LocalTime startTime2 = LocalTime.parse("15:00:00",formatter);
        slot2.setStartTime(startTime2);
        slot2.setDuration(1);
        slot2.setSlotType(SlotType.YOGA);
        slot2.setDurationUnit(DurationUnit.HOUR);



        Center center2 = new Center();
        center2.setCenterId(UUID.randomUUID());
        center2.setCenterName("Marathalli");
        center2.setStatus(Status.ACTIVE);

        bookingService.addCenter(center2);

        bookingService.addSlot(center.getCenterId(), slot1);
        bookingService.addSlot(center.getCenterId(), slot2);

        bookingService.addSlot(center2.getCenterId(), slot2);
        bookingService.addSlot(center2.getCenterId(), slot);

        User user = new User();
        user.setEmail("pk@gmail.com");
        user.setPassword("password");
        user.setUserId(UUID.randomUUID());

        List<Slot> slotList = bookingService.getAvailableSlots(SlotType.RUNNING, "bellandur");

    }
}
