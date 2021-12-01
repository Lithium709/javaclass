package com.google.algorithms;

import java.util.Arrays;

import java.util.Random;


public class QuickSelect {

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static void qsort(int[] arr, int low, int high) {
        if(low < high) {
            int p = partition(arr, low, high);
            qsort(arr, low, p - 1);
            qsort(arr, p + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int pivotloc = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, pivotloc++);
            }
        }
        swap(arr, high, pivotloc);
        return pivotloc;
    }

    static int kthSmallest(int[] arr, int low, int high, int k) {
        int partition = partition(arr, low, high);
        if (partition == k) {
            return arr[partition];
        } else if (partition < k) {
            return kthSmallest(arr, partition + 1, high, k);
        } else {
            return kthSmallest(arr, low, partition - 1, k);
        }
    }

    static int min(int[] arr) {
        return kthSmallest(arr, 0, arr.length - 1, 0);
    }

    static int max(int[] arr) {
        return kthSmallest(arr, 0, arr.length - 1, arr.length - 1);
    }

    static int median(int[] arr) {
        int n = arr.length;
        if (n % 2 == 0) {
            return kthSmallest(arr, 0, n - 1, n / 2);
        } else {
            return (kthSmallest(arr, 0, n - 1, n / 2) + kthSmallest(arr, 0, n - 1, n / 2)) / 2;
        }
    }

    static int activityNotifications(int[] expenditure, int d) {

        int n = expenditure.length;
        int notification = 0;
        for (int i = d; i < n; i++) {
            int[] sub = Arrays.copyOfRange(expenditure, i - d, i);
            Arrays.sort(sub);
            int median = d % 2 == 0 ? (sub[d / 2 - 1] + sub[d / 2 + 1]) / 2 : sub[d / 2];
            //System.out.println("value = " + expenditure[i]);
            // System.out.println("median =" + median);
            if (expenditure[i] >= 2 * median) {
                notification++;
            }
        }
        return notification;

    }


    public static void main(String[] args) {

        int[] a = new int[]{2, 3, 4, 2, 13, 6, 8, 4, 5, 1};
        activityNotifications(a, 5);
      //  System.out.println(max(a));
      //  System.out.println(min(a));
      //  System.out.println(median(a));
        qsort(a, 0, a.length -1);
        for (int i = 0; i < a.length ; i++) {
            System.out.println(a[i]);
        }

       // Arrays.fill();
      //  Random random = new Random();
      //  random.nextInt(n);
    }

}
