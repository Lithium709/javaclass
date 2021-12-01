package com.company.leetcode;

public class FindAPeak {


    public static int findPeakElement(int[] nums) {

        int lo = 0;
        int hi = nums.length - 1;
        while (hi > lo) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }


    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));
    }

}
