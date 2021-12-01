package com.company.leetcode;

public class MaxHeap {

    private int size = 0;
    private int[] arr;

    public MaxHeap(int maxSize) {
        this.arr = new int[maxSize];
    }

    private void swap(int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    private void swim(int index) {
        if (index == 0) {
            return;
        }
        if (arr[index] > arr[index / 2]) {
            swap(index, index / 2);
            swim(index / 2);
        }
    }

    private void sink(int index) {
        if (index >= size) {
            return;
        }
        if (arr[2 * index] > arr[2 * index + 1]) {
            //
            if (arr[index] < arr[2 * index]) {
                swap(index, 2 * index);
                sink(2 * index);
            }
        } else {
            if (arr[index] < arr[2 * index + 1]) {
                swap(index, 2 * index + 1);
                sink(2 * index + 1);
            }
        }
    }

    public void offer(int value) {
        arr[size++] = value;
        swim(size - 1);
    }

    public int peek() {
        return arr[0];
    }

    public int poll() {
        swap(0, size - 1);
        sink(0);
        return arr[--size];
    }

    public static void main(String[] args) {

        MaxHeap heap = new MaxHeap(10);
        int[] data = new int[]{5, 13, 54, 3, 4};

        for (int d : data) {
            heap.offer(d);
        }
        System.out.println(heap.peek());
        heap.poll();
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }
}
