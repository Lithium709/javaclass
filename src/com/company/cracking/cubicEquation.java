package com.company.cracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// find all a^3 + b^3 = c^3 + d^3
public class cubicEquation {


    public static void main(String[] args) {

        // printAll_naive(10);

        printAll_map(10);

    }

    //
    static void printAll_naive(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (i * i * i + j * j * j == k * k * k + l * l * l) {
                            System.out.println(String.format("%s %s %s %s", i, j, k, l));
                        }
                    }
                }
            }
        }
    }

    static void printAll_map(int n) {

        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int key = i * i * i + j * j * j;
                final List<Integer> list = Arrays.asList(i, j);
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(list);
            }
        }

        for (Map.Entry<Integer, List<List<Integer>>> entry : map.entrySet()) {
            for (List<Integer> list1 : entry.getValue()) {
                for (List<Integer> list2 : entry.getValue()) {
                    System.out.println(
                            String.format("%s %s %s %s", list1.get(0), list1.get(1), list2.get(0), list2.get(1)));
                }
            }
        }

    }

}
