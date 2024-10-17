package com.learning.java.lld.flipkartgymbooking.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {


    private Map<String, RankingStrategy> rankingStrategyMap;

    public StrategyFactory() {
        rankingStrategyMap = new HashMap<>();
        rankingStrategyMap.put("distance", new DistanceBasedRanking());
        rankingStrategyMap.put("time", new TimeBasedRanking());
    }
    public RankingStrategy getRankingStrategy(String rankType) {
        return rankingStrategyMap.get(rankType);
    }
}
