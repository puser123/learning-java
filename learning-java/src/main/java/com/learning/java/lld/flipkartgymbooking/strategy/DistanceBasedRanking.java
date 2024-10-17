package com.learning.java.lld.flipkartgymbooking.strategy;

import com.learning.java.lld.flipkartgymbooking.model.Center;
import com.learning.java.lld.flipkartgymbooking.model.Slot;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class DistanceBasedRanking implements RankingStrategy{

    @Override
    public Map<UUID, Slot> rank(List<ImmutablePair<UUID, Slot>> slots, UUID centerId, Map<UUID, Center> centerMap) {

        double EARTH_RADIUS = 6371;
        Center center = centerMap.get(centerId);
        slots.removeIf(slot -> slot.getLeft().equals(center.getCenterId()));
        PriorityQueue<ImmutablePair<UUID, Slot>> priorityQueue = new PriorityQueue<>((b, a) -> {
            double lat0 = center.getAddress().getLatitude();
            double long0 = center.getAddress().getLongitude();

            double lat1 = centerMap.get(a.getLeft()).getAddress().getLatitude();
            double long1 = centerMap.get(a.getLeft()).getAddress().getLongitude();
            double lat2 = centerMap.get(b.getLeft()).getAddress().getLatitude();
            double long2 = centerMap.get(b.getLeft()).getAddress().getLongitude();
            double x1 = (Math.toRadians(long1) - Math.toRadians(long0)) * Math.cos(Math.toRadians(lat1) +
                    Math.toRadians(lat0))/2;
            double y1 = Math.toRadians(lat1) - Math.toRadians(lat0);
            double d1 = Math.sqrt(x1 * x1 + y1 * y1) * EARTH_RADIUS;
            double x2 = (Math.toRadians(long2) - Math.toRadians(long0)) * Math.cos(Math.toRadians(lat2) +
                    Math.toRadians(lat0))/2;
            double y2 = Math.toRadians(lat2) - Math.toRadians(lat0);
            double d2 = Math.sqrt(x2 * x2 + y2 * y2) * EARTH_RADIUS;
            return d1 < d2 ? 1:0;
        });

        slots.forEach(uuidSlotImmutablePair ->  {
            priorityQueue.add(uuidSlotImmutablePair);
        });
        // return top 5 from heap.
        Map<UUID, Slot > slotMap = new HashMap<>();
        int count=  0;
        while(!priorityQueue.isEmpty() && count < 5) {
            ImmutablePair<UUID, Slot> slotImmutablePair = priorityQueue.poll();
            slotMap.put(slotImmutablePair.getKey(), slotImmutablePair.getValue());
            count++;
        }
        return slotMap;
    }
}
