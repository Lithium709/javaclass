package com.google.algorithms;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class UnionFind {

    private int m;

    private int[] id;

    private int[] sz;

    public UnionFind(int m) {
        this.m = m;
        id = new int[m];
        sz = new int[m];
        for (int i = 0; i < m; i++) {
            id[i] = i;
        }
    }

    public void connect(int a, int b) {
        if (sz[a] > sz[b]) {
            id[a] = b;
            sz[a] += sz[b];
        } else {
            id[b] = a;
            sz[b] += sz[a];
        }
    }


    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,4};
        final IntSummaryStatistics intSummaryStatistics = Arrays.stream(array).summaryStatistics();
        System.out.println(intSummaryStatistics.getAverage());

    }

}
