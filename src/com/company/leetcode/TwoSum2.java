package com.company.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TwoSum2 {


    Stack<String> stack_num = new Stack<String>();

    public static int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        Map<Integer, Integer> map = new HashMap<>();

        // O(n*log n)
        // O(n)
        for (int i = 0; i < nums.length; i++) {

            if (map.get(nums[i]) != null) {
                res[0] = i;
                res[1] = map.get(nums[i]);
                return res;
            }

            map.put(target - nums[i], i);

        }
        return res;

    }


    public static void main(String[] args) {

        System.out.println(twoSum(new int[]{2, 7, 11, 15}, 9));

    }

}
