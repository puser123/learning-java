package com.learning.java.lld.subscription_Atlassian.model;

import com.learning.java.lld.subscription_Atlassian.model.enums.PlanType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerSubscription {
    private PlanType planId;
    private LocalDate startDate;
}
