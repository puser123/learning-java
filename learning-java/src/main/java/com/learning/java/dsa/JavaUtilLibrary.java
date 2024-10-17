package com.learning.java.dsa;

import java.util.LinkedHashMap;

public class JavaUtilLibrary {
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        map.put(1,50);
        map.put(2,100);
        map.put(1,100);
        map.put(3,150);

        map.entrySet()
                .stream().forEach(v -> System.out.println(v.getKey() + " Value: " + v.getValue()));

    }
}
