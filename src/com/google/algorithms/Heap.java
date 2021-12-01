package com.google.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Heap {

    // private int[] arr = new int[1000];

    static void heapify(int[] arr) {

    }

    public static void main(String[] args) {

        int[] arr = new int[]{2, 34, 5, 6, 1, 56, 3};
        Map<Integer, Integer> mp = new HashMap<>();

        mp.merge(1, 1, Integer::sum);
        mp.merge(1, 1, Integer::sum);
        mp.merge(1, 1, Integer::sum);

        System.out.println(mp.get(1));
    }

}
