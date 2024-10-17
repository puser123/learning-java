package com.learning.java.lld.flipkartgymbooking.strategy;

import com.learning.java.lld.flipkartgymbooking.model.Center;
import com.learning.java.lld.flipkartgymbooking.model.Slot;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeBasedRanking implements RankingStrategy{

    @Override
    public Map<UUID, Slot> rank(List<ImmutablePair<UUID, Slot>> slots, UUID centerId, Map<UUID, Center> centerMap) {

        PriorityQueue<ImmutablePair<UUID, Slot>> priorityQueue = new PriorityQueue<>((b,a) -> {
            return a.getRight().getStartTime().compareTo(b.getRight().getStartTime()) < 0 ? 1: 0;
        });
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
