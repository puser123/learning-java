package com.learning.java.lld.subscription_Atlassian.model;

import com.learning.java.lld.subscription_Atlassian.model.enums.PlanType;
import lombok.Data;
import lombok.Getter;

@Data
public class Subscription {
    private PlanType planId;
    private Price monthlyCost;
}
