package com.learning.java.lld.flipkartgymbooking;

import com.learning.java.lld.flipkartgymbooking.exception.ValidationException;
import com.learning.java.lld.flipkartgymbooking.model.Center;
import com.learning.java.lld.flipkartgymbooking.model.Slot;
import com.learning.java.lld.flipkartgymbooking.model.SlotType;
import com.learning.java.lld.flipkartgymbooking.model.Status;
import com.learning.java.lld.flipkartgymbooking.strategy.StrategyFactory;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class BookingService {

    private Map<UUID, Center> centerMap;
    private Map<UUID, Queue<UUID>> waitingQueuePerSlotId;
    private StrategyFactory strategyFactory;
    private Map<UUID, Map<LocalDate, List<UUID>>> bookedIds; // mapping of centerId, and slot Id; (userId -> bookingId)
    private Map<UUID, ImmutablePair<Center, Slot>> bookingIdMap;// booking Id -> slotId, centerId;

    BookingService(StrategyFactory factory) {
        centerMap = new HashMap<>();
        waitingQueuePerSlotId = new HashMap<>();
        this.strategyFactory = factory;
        this.bookedIds = new HashMap<>();
    }

    List<Slot> getAvailableSlots(SlotType slotType, String centerName) {
        Optional<Center> center = centerMap
                .entrySet()
                .stream().filter(uuidCenterEntry -> centerName.equalsIgnoreCase( uuidCenterEntry.getValue().getCenterName()))
                .map(Map.Entry::getValue)
                .findAny();
        if(center.isPresent()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);
            return center.get().getSlotMap()
                    .get(date).entrySet()
                    .stream().filter(uuidSlotEntry -> slotType.equals( uuidSlotEntry.getKey()))
                    .map(Map.Entry::getValue)
                    .filter(slot -> slot.isAvailable())
                    .collect(Collectors.toList());
        } else {
            throw new ValidationException(400, 1001, "CENTER_DOES_NOT_EXISTS");
        }
    }

    public boolean bookSlot(UUID userId, UUID slotId, UUID centerId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);

        Optional<Slot> slot = centerMap.get(centerId)
                .getSlotMap()
                .get(date)
                .entrySet()
                .stream().filter(slotTypeSlotEntry -> slotTypeSlotEntry.getValue().getSlotId().equals(slotId))
                .map(Map.Entry::getValue)
                .findFirst();
        if(!slot.isPresent()) {
            throw new ValidationException(400, 1002, "SLOT_NOT_PRESENT");
        } else if(!slot.get().isAvailable()) {
            throw new ValidationException(400, 1002, "SLOT_NOT_AVAILABLE");
        } else {
            boolean reserveSlot =  slot.get().reserveSeat();
            UUID bookingId = UUID.randomUUID();
            bookingIdMap.put(bookingId, new ImmutablePair(centerMap.get(centerId), slot));
            Map<LocalDate, List<UUID>> bookingsPerDay = bookedIds.getOrDefault(userId, null);
            if(Objects.isNull(bookingsPerDay)) {
                bookedIds.put(userId, new HashMap<>());
            }
            if(Objects.nonNull(bookingsPerDay.get(date))) {
                bookedIds.get(userId).put(date, new ArrayList<>(3)); // only three booking per days.
            }
            bookedIds.get(userId).get(date).add(bookingId);

            return reserveSlot;
        }
    }

    Map<UUID, Slot> recommendedSlots(SlotType slotType, UUID centerId, UUID userId, String rankingType) {
        Center center = centerMap.get(centerId);
        // get All available slots by slot Type.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);

        List<ImmutablePair<UUID, Slot>> centers = getAllCityWithAvailableSlots(date, slotType);
        // rank them based on ranking Type.
       return this.strategyFactory.getRankingStrategy(rankingType)
               .rank(centers, centerId, centerMap);
    }

    private List<ImmutablePair<UUID, Slot>> getAllCityWithAvailableSlots(LocalDate date, SlotType slotType) {
        List<ImmutablePair<UUID, Slot>> list= new ArrayList<>();
        centerMap
                .entrySet()
                .stream().forEach(uuidCenterEntry -> uuidCenterEntry.getValue()
                        .getSlotMap().get(date).entrySet()
                        .stream().filter(slotTypeSlotEntry -> slotType.equals(slotTypeSlotEntry.getValue().getSlotType())
                        && slotTypeSlotEntry.getValue().isAvailable())
                        .forEach(res -> {
                            list.add(new ImmutablePair(uuidCenterEntry.getKey(), res.getValue()));
                        }));
        return list;
    }

    List<SlotType> getSlotTypeByCenterName(String centerName) {
        Optional<Center> center = centerMap
                .entrySet()
                .stream().filter(uuidCenterEntry -> centerName.equalsIgnoreCase( uuidCenterEntry.getValue().getCenterName()))
                .map(Map.Entry::getValue)
                .findAny();
        if(!center.isPresent()) {
            throw new ValidationException(400, 1001, "CENTER_DOES_NOT_EXISTS");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);
        return center.get()
                .getSlotMap()
                .get(date)
                .entrySet().stream().map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    boolean addCenter(Center center) {
        centerMap.put(center.getCenterId(), center);
        return true;
    }

    boolean deleteCenter(UUID centerId) {
        if(Objects.isNull(centerMap.getOrDefault(centerId, null))) {
            throw new ValidationException(400, 1001, "CENTER_DOES_NOT_EXISTS");
        } else {
            Center center = centerMap.get(centerId);
            center.setStatus(Status.DELETED);
            return true;
        }

    }

    public List<ImmutablePair<Center, Slot>> getAllBookingByUserId(UUID userId, LocalDate date) {

        return Optional.ofNullable(bookedIds.getOrDefault(userId, null))
                .map(map -> map.get(date))
                .map(bookingIds -> bookingIds.stream().map(id -> bookingIdMap.get(id))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    boolean addSlot(UUID centerId, Slot slot) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);
        Map<SlotType, Slot> slotMap = centerMap.get(centerId).getSlotMap()
                .get(date);
        if(Objects.isNull(slotMap)) {
            centerMap.get(centerId).getSlotMap().put(date, new HashMap<>());
        }
        centerMap.get(centerId).getSlotMap().get(date).put(slot.getSlotType(), slot);
        return true;
    }

}
