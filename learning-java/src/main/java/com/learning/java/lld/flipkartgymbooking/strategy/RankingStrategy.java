package com.learning.java.lld.flipkartgymbooking.strategy;

import com.learning.java.lld.flipkartgymbooking.model.Center;
import com.learning.java.lld.flipkartgymbooking.model.Slot;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RankingStrategy {

    Map<UUID, Slot> rank(List<ImmutablePair<UUID, Slot>> slots, UUID centerId, Map<UUID, Center> centerMap);
}
