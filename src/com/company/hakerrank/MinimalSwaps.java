package com.company.hakerrank;

import java.util.Arrays;

public class MinimalSwaps {

    public static void main(String[] args) {

        System.out.println(minimumSwaps(
                new int[]{1, 3, 5, 2, 4, 6, 8}));

    }


    // Complete the minimumSwaps function below.
    static int minimumSwaps2(int[] arr) {

        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        int missmatch = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sorted[i] != arr[i]) {
                ++missmatch;
            }
        }
        return missmatch == 0 ? 0 : missmatch - 1;
    }

    static int minimumSwaps(int[] arr) {

        int[] big_arr = new int[100_000];

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            big_arr[i] = arr[i];
            max = Math.max(max, arr[i]);
        }

        int i, c = 0, n = 100_000;
        for (i = 0; i < max; i++) {
            if (big_arr[i] == (i + 1)) {
                continue;
            }
            if (big_arr[i] != 0 && big_arr[big_arr[i] - 1] !=0 )  {
                swap(big_arr, i, big_arr[i] - 1);
                c++;
                i--;
            }

        }
        return c;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
