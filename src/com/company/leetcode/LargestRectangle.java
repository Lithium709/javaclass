package com.company.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangle {

    public static int largestRectangleArea(int[] heights) {

        Deque<Integer> stack = new ArrayDeque<>();

        int max = Integer.MIN_VALUE;
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            final int m = heights[stack.pop()] * (heights.length - stack.peek() - 1);
            max = Math.max(max, m);
        }

        return max;
    }


    public static void main(String[] args) {
      //  System.out.println(largestRectangleArea(new int[]{1, 1}));
      //  System.out.println(largestRectangleArea(new int[]{1, 9}));
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

}
