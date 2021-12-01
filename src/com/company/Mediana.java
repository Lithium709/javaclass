package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Mediana {

    static Queue<Integer> minHeap = new PriorityQueue<>();
    static Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    static double mediana = 0;

    // 0, 1, 2, 3
    static int median2(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        return n % 2 == 0 ? (a[n / 2 - 1] + a[n / 2]) / 2 : a[n / 2];
    }


    static void median(int[] a) {

        int newVal = a[0];
        maxHeap.offer(newVal);
        mediana = newVal;
        System.out.println(newVal);
        for (int i = 1; i < a.length; ) {
            newVal = a[i++];
            if (mediana > newVal) {
                maxHeap.offer(newVal);
                if (i % 2 == 1) {
                    mediana = maxHeap.peek();
                }
            } else {
                minHeap.offer(newVal);
                if (i % 2 == 1) {
                    mediana = minHeap.peek();
                }
            }
            // rebalance
            if (i % 2 == 0) {
                if (maxHeap.size() > minHeap.size()) {
                    minHeap.offer(maxHeap.poll());
                } else if (maxHeap.size() < minHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                }
                mediana = ((double) minHeap.peek() + maxHeap.peek()) / 2;
            }

            System.out.println(mediana);
        }

    }

    public static void main(String[] args) {

        /*
        Queue<Integer> queue = minQueue;
        System.out.println(queue.peek());
        queue.offer(12);
        System.out.println(queue.peek());
        queue.offer(4);
        System.out.println(queue.peek());
        queue.offer(5);
        System.out.println(queue.peek());
        queue.offer(7);
        queue.poll();
        System.out.println(queue.peek());

        12
4
5
3
8
7


        */
        int[] a = {94455,
                20555,
                20535,
                53125,
                73634,
                148};
        median(a);


        System.out.println(median2(a)) ;

    }

}
