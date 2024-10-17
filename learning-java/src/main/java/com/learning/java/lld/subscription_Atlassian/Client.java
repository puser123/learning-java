package com.learning.java.lld.subscription_Atlassian;

import java.util.List;

public class Client {


    public static void main(String[] args) {
        CostExplorer costExplorer = new CostExplorer();

        System.out.println(costExplorer.monthlyCostList());
        System.out.println(costExplorer.annualCost());
    }
}
