package com.learning.java.lld.subscription_Atlassian;

import com.learning.java.lld.subscription_Atlassian.model.*;
import com.learning.java.lld.subscription_Atlassian.model.enums.PlanType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CostExplorer {
    private List<Subscription> subscriptions;
    private Customer customer;

    CostExplorer() {
        initialize();
    }
    void initialize()   {
        customer = new Customer();
        customer.setCustomerId("c1");
        Product product =new Product();
        CustomerSubscription customerSubscription = new CustomerSubscription();
        customerSubscription.setPlanId(PlanType.BASIC);
        customerSubscription.setStartDate(LocalDate.parse("2021-05-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        product.setCustomerSubscription(customerSubscription);
        product.setProductName("JIRA");
        customer.setProduct(product);
        subscriptions = new ArrayList<>(3);
        subscriptions.add(createSubscription(9.99, PlanType.BASIC));
        subscriptions.add(createSubscription(49.99, PlanType.STANDARD));
        subscriptions.add(createSubscription(249.99, PlanType.PREMIUM));

    }

    List<Double> monthlyCostList() {
        // Return monthly cost by customer.
        Optional<Subscription> subscription = subscriptions
                .stream().filter(sub -> sub.getPlanId().equals(customer.getProduct().getCustomerSubscription().getPlanId()))
                .findFirst();
        List<Double> prices = new ArrayList<>(12);
        for(int i=0;i<12;i++)
            prices.add(i, 0.0);

        if(subscription.isPresent()) {
            int month = customer.getProduct().getCustomerSubscription().getStartDate().getMonthValue();
            for(int m = month;m<13;m++) {
                prices.set(m-1, subscription.get().getMonthlyCost().getValue());
            }
        }
        return prices;
    }

    public double annualCost() {
        Optional<Subscription> subscription = subscriptions
                .stream().filter(sub -> sub.getPlanId().equals(customer.getProduct().getCustomerSubscription().getPlanId()))
                .findFirst();
        double totalPrice = 0;

        if(subscription.isPresent()) {
            int month = customer.getProduct().getCustomerSubscription().getStartDate().getMonthValue();
            for(int m = month;m<13;m++) {
                totalPrice += subscription.get().getMonthlyCost().getValue();
            }
        }
        return totalPrice;
    }

    Subscription createSubscription(double value, PlanType planType) {
        Subscription subscription = new Subscription();
        Price price = new Price();
        price.setValue(value);
        price.setCurrencyType("DOLLAR");
        subscription.setMonthlyCost(price);
        subscription.setPlanId(planType);
        return subscription;
    }
}
