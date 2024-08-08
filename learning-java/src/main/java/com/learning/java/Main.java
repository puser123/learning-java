package com.learning.java;

import com.learning.java.designpattern.SingleTonDesignPattern;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@Slf4j
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        SingleTonDesignPattern se = SingleTonDesignPattern.getInstance();
        log.info("SE : {}", se.getDescription());
        SingleTonDesignPattern se1 = SingleTonDesignPattern.getInstance();
        log.info("SE1 : {}", se1.getDescription());
        List<String> l = new ArrayList<>();
    }
}