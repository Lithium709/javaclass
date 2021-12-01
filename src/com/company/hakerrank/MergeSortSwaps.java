package com.company.hakerrank;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSortSwaps {


    static long sort(int[] arr, int lo, int hi) {
        long count = 0;
        if (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            count += sort(arr, lo, mid);
            count += sort(arr, mid + 1, hi);
            count += merge(arr, lo, mid, hi);
        }
        return count;
    }


    static long merge(int[] arr, int leftStart, int mid, int rightEnd) {

        int[] left = Arrays.copyOfRange(arr, leftStart, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, rightEnd + 1);
        int li = 0;
        int ri = 0;
        int i = leftStart;
        long count = 0;
        while (li < left.length && ri < right.length) {

            if (left[li] <= right[ri]) {
                arr[i++] = left[li++];
            } else {
                arr[i++] = right[ri++];
                count += (mid + 1) - (leftStart + li);
            }
        }

        while (li < left.length) {
            arr[i++] = left[li++];
        }

        while (ri < right.length) {
            arr[i++] = right[ri++];
        }
        return count;
    }

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {

        return sort(arr, 0, arr.length - 1);
    }

    private static final Scanner scanner = new Scanner(System.in);
    /*
    public static void main(String[] args) {

        // int[] a = new int[]{6, 12, 5, 6, 23, 67, 4, 2, 14, 1};
          int[] a = new int[]{2,4,1};
        //  int[] a = new int[]{1, 1, 1, 2, 2};
        // int[] a = new int[]{2, 1, 3, 1, 2};

        int swapCounter = sort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            System.out.print(",");
        }
        System.out.print("Swaps = ");
        System.out.println(swapCounter);

    }
    */
     public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(System.out);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            System.out.println(String.valueOf(result));
         //   bufferedWriter.write(String.valueOf(result));
         //   bufferedWriter.newLine();
        }

       // bufferedWriter.close();

        scanner.close();
    }
}
