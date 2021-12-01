package com.company;

public class Kadane {

    public static int getMaxSubarraySum(int[] array){
        int currentMax = Integer.MIN_VALUE;
        int totalMax =  Integer.MIN_VALUE;

        for(int i = 0; i < array.length; i++){
            currentMax = Math.max(currentMax, 0) + array[i];
            totalMax = Math.max(totalMax, currentMax);
        }
        return totalMax;
    }


    public static int dfs(int[] arr, int start, int end) {

        int max = Integer.MIN_VALUE;

        for (int i = start; i < end; i++) {
            final int max1 = Math.max(0, dfs(arr, start, i));
            System.out.println(max1);
            max = Math.max(max, arr[i] + max1);
        }
        return max;
    }

    public static int maxSum(int[] arr) {

        return dfs(arr, 0, arr.length);
    }


    public static void main(String[] args) {

        System.out.println(maxSum(new int[]{1, 2, 3, -2, 5}));
   //     System.out.println(getMaxSubarraySum(new int[]{1, 2, 3, -2, 5}));
   //     System.out.println(maxSum(new int[]{-1, -2, -3, -4}));
   //     System.out.println(getMaxSubarraySum(new int[]{-1, -2, -3, -4}));

    }


}
