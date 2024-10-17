package com.learning.java.lld.flipkartgymbooking.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class Slot {
    private UUID slotId;
    private SlotType slotType;
    private LocalTime startTime;
    private int duration;
    private DurationUnit durationUnit;
    private int seatCount;


    public boolean isAvailable() {
        return seatCount > 0;
    }

    public boolean reserveSeat() {
        if(seatCount > 0) {
            seatCount--;
            return true;
        }  else {
            return false;
        }
    }
}
