package com.learning.java.lld.flipkartgymbooking.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Center {

    private UUID centerId;
    private String centerName;
    private Status status;
    private Address address;
    private int maxSlots;
    private Map<LocalDate, Map<SlotType, Slot>> slotMap;

    public Center() {
        slotMap = new HashMap<>();
    }
}
