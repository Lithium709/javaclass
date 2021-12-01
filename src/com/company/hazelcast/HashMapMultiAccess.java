package com.company.hazelcast;

import java.util.HashMap;
import java.util.Map;

public class HashMapMultiAccess {


    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();

        final Runnable addValue = () -> map.put("a", 1);
        final Runnable removeValue = () -> map.remove("a");
        for (int i = 0; i < 100; i++) {
            new Thread(addValue).start();
            new Thread(removeValue).start();
        }

        System.out.println(map);

    }


}
