package com.company.reintech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class MinimalDiscount {


    public static void main(String[] args) {

        List<Integer> prices = new ArrayList<>(Arrays.asList(5, 1, 3, 4, 6, 2));
        prices.stream().mapToInt(x -> x).sum();

        // teamFormation(prices, 2, 2);

    }

    public static void finalPrice(List<Integer> prices) {

        Integer total = 0;
        List<Integer> noDiscount = new ArrayList<>();
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = prices.size() - 1; i >= 0; i--) {
            int discount = 0;
            Integer current = prices.get(i);
            final Integer head = queue.peek();
            if (head != null && current >= head) {
                discount = head;
            }
            queue.offer(current);
            if (discount == 0) {
                noDiscount.add(prices.size() - i);
            }
            total += current - discount;

        }
        System.out.println(total);
        System.out.print(String.join(" ", noDiscount.stream().map(String::valueOf).collect(Collectors.toList())));
    }

    public static void finalPriceN2(List<Integer> prices) {

        Integer total = 0;
        List<Integer> noDiscount = new ArrayList<>();
        // n^2
        for (int i = 0; i < prices.size(); i++) {

            int discount = 0;
            for (int j = i + 1; j < prices.size(); j++) {
                if (prices.get(i) >= prices.get(j)) {
                    discount = prices.get(j);
                    break;
                }
            }
            if (discount == 0) {
                noDiscount.add(i);

            }
            total += prices.get(i) - discount;
        }

        System.out.println(total);
        System.out.print(String.join(" ", noDiscount.stream().map(String::valueOf).collect(Collectors.toList())));
    }


}
