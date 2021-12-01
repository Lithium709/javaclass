package com.company.leetcode;

import java.util.Arrays;

/**
 *  Weighted Union Find Algorithm with path compression
 */
public class UnionFind {

    private int[] arr = new int[10000];

    public UnionFind() {
        // negative sign means link to itself and absolute number means count of elements in the set
        Arrays.fill(arr, -1);
    }

    public void print(){
        System.out.print('[');
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i]);
            System.out.print(',');
        }
        System.out.print(']');
        System.out.println();
    }

    /**
     *    Performs union of two elements x and y.
     * @param x id of first element
     * @param y id of second element
     * @return false if the sets are already joined
     */
    public boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if(px == py) return false;
        if(arr[px] < arr[py]) {
            arr[px] += arr[py];
            arr[py] = px;
        } else {
            arr[py] += arr[px];
            arr[px] = py;
        }
        return true;
    }

    /**
     * Finds the parent element. Performs path compressing on the way
     * @param x id of an element
     * @return
     */
    public int find(int x) {
        int i = x;
        while (arr[i] >= 0) {
            i = arr[i];
            arr[x] = i; // path compression
        }
        return i;
    }

    public static void main(String[] args) {

        UnionFind uf = new UnionFind();
        uf.union(1, 0);
        uf.union(2, 1);

        uf.print();    }
}
