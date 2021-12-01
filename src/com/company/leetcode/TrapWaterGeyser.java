package com.company.leetcode;

public class TrapWaterGeyser {


    private static int trap(int[] heights, int geysers[]) {

        if (geysers.length == 0) {
            return 0;
        }
        int n = heights.length;
        int left = 0;
        int right = n - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int[] water = new int[n];
        boolean[] geyserCoverage = new boolean[n];
        for (int i : geysers) {
            geyserCoverage[i] = true;
        }

        while (left < right) {
            maxLeft = Math.max(maxLeft, heights[left]);
            maxRight = Math.max(maxRight, heights[right]);

            if (maxLeft < maxRight) {
                final int dw = maxLeft - heights[left];

                if (heights[left] < maxLeft) {
                    water[left] += dw;
                    System.out.println("dw = " + dw + " at " + left);
                }
                left++;
            } else {
                final int dw = maxRight - heights[right];
                if (heights[right] < maxRight) {
                    water[right] += dw;
                    System.out.println("dw = " + dw + " at " + right);
                }
                right--;
            }
        }

        for (int i : geysers) {

            // go left
            left = i - 1;
            int level = water[i] + heights[i];
            while (left > 0) {
                if (heights[left] > level) {
                    break;
                } else {
                    geyserCoverage[left] = true;
                }
                left--;
            }
            right = i + 1;
            while (right < n) {
                if (heights[right] > level) {
                    break;
                } else {
                    geyserCoverage[right] = true;
                }
                right++;
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (geyserCoverage[i]) {
                ret += water[i];
            }
        }

        return ret;
    }

    public static void main(String[] args) {

        int trap = trap(new int[]{1, 0, 2, 1, 2, 3, 2, 0, 2, 5, 4, 4, 5, 3, 2, 2, 4, 3}, new int[]{8, 14});
        System.out.println(trap);
        assert trap == 12;
         trap = trap(new int[]{1, 0, 2, 1, 2, 3, 2, 0, 2, 5, 4, 4, 5, 3, 2, 2, 4, 3}, new int[]{8});
        System.out.println(trap);
        assert trap == 7;
    }

}
