package com.company.leetcode;

import java.util.Arrays;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        Arrays.sort(nums);

        // O(n*log n)
        for (int i = 0; i < nums.length; i++) {

            int lo = i + 1;
            int hi = nums.length;

            int s = Arrays.binarySearch(nums, lo, hi, target - i);

            if (s > 0) {
                res[0] = i;
                res[1] = target - i;
                return res;
            }
        }
        return res;
    }


    public static void main(String[] args) {

        System.out.println(twoSum(new int[]{2,7,11,15},9));

    }

}
