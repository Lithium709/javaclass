package com.company.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrapWater {

    public static int trap(int[] height) {

        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int water = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int dist = i - stack.peek() - 1;
                int h = Math.min(height[stack.peek()], height[i]) - height[top];
                System.out.println("i= " + i + ", h= " + h + ", d= " + dist);
                water += h * dist;
            }
            stack.push(i);
        }
        return water;
    }

    public static void main(String[] args) {
        trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }
}
