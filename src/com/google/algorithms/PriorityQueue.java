package com.google.algorithms;

import java.util.NoSuchElementException;
import java.util.TreeSet;

public class PriorityQueue {

    private int size = 0;

    private int[] pq;

    public PriorityQueue(int capacity) {
        pq = new int[capacity];
    }

    public PriorityQueue() {
        pq = new int[100];
    }

    public int getTop() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        return pq[1];
    }

    private void swap(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public void push(int x) {
        pq[++size] = x;
        int k = size;
        while (k > 1 && pq[k] > pq[k / 2]) {
            swap(k, k / 2);
            k /= 2;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(pq[i]);
            System.out.print(",");
        }
        System.out.println();
    }

    public void delete(int x) {

    }

    public int pop() {
        int res = getTop();
        swap(1, size--);
        int k = 1;
        while (k < size && pq[k] < Math.max(pq[k * 2], pq[k * 2 + 1])) {
            if (pq[k * 2] > pq[k * 2 + 1]) {
                swap(k, 2 * k);
                k = 2 * k;
            } else {
                swap(k, 2 * k + 1);
                k = 2 * k + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        PriorityQueue pq = new PriorityQueue();

        pq.push(5);
        pq.push(12);
        pq.push(3);
        pq.push(33);
        pq.push(2);
        pq.push(20);



        System.out.println(pq.pop());
        System.out.println(pq.pop());

    }
}
