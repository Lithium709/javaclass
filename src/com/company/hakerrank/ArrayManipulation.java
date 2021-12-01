package com.company.hakerrank;

public class ArrayManipulation {


    public static void main(String[] args) {

        int[][] q = {
                {1, 2, 100},
                {2, 5, 100},
                {3, 4, 100}};

        System.out.println(arrayManipulation(5, q));


    }


    static long arrayManipulation(int n, int[][] queries) {

        int[] arr = new int[n + 1];

        for (int i = 0; i < queries.length; i++) {

            final int left = queries[i][0];
            final int right = queries[i][1];
            final int number = queries[i][2];
            arr[left - 1] += number;
            arr[right] -= number;
        }

        long x = 0;
        long max = 0;
        for (int i = 0; i <= n; i++) {
            x += arr[i];
            max = Math.max(max, x);
        }

        return max;

    }
}
