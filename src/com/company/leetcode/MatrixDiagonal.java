package com.company.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.zip.CRC32;

public class MatrixDiagonal {


    public static int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        int dir = 1;
        // r--  UP
        // c++
        // r++
        // c-- DOWN
        /*
        [[1,2,3,5,6],
         [4,5,6,5,6],
         [7,8,9,5,6],
         [5,5,5,5,6],
         [1,1,1,1,1]]

         [[1,2,3],
          [4,5,6],
          [7,8,9]]
        */
        int row = 0;
        int col = 0;
        for(int i = 0; i < m*n; i++) {
            ans[i] = matrix[row][col];
            int r = row - dir;
            int c = col + dir;
            if(c < 0 || r < 0 || r >= m || c >= n) {
                if(dir == 1) { // hit up
                    row += r == n - 1? 1 :0;
                    col += c < n - 1? 1: 0;
                } else {       // hit down
                    col += r == m - 1? 1: 0;
                    row += r < m - 1? 1: 0;
                }
                dir = -dir;
            } else {
                row = r;
                col = c;
            }
        }
        return ans;

    }

    public int[][] merge(int[][] intervals) {

        int n = intervals.length;
        if (n == 0 || n == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (a,b)-> a[0] - b[0]);
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] interval :intervals) {
            if(merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE) {
            if(divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if(divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }

        if(dividend > 0 && divisor > 0 ) {
            return divide(-dividend, -divisor);
        }
        if(dividend > 0 ) {
            return -divide(-dividend, divisor);
        }
        if(divisor > 0 ) {
            return -divide(dividend, -divisor);
        }
        int ans = 0;
        int min2 = Integer.MIN_VALUE >> 1;
        while(dividend <= divisor) {
            int p = 0;
            int x = divisor;
            while(dividend <= x + x && x > min2) {
                x <<= 1;
                p++;
            }
            dividend -= x;
            ans += 1 << p;
        }
        return ans;
    }


    public static void main(String[] args) {

        int[][] m = new int[][] {{1,2,3},{4,5,6}, {7,8,9}};
        for (int i :findDiagonalOrder(m) ) {
            System.out.println(i);
        }
        CRC32 crc = new CRC32();
        crc.update(new byte[]{1,2,3}, 0, 1);
        System.out.println(crc.getValue());
        crc.update(new byte[]{1,2,3}, 1, 1);
        System.out.println(crc.getValue());
        crc.update(new byte[]{1,2,3}, 2, 1);
        crc.update(new byte[]{1,2,3}, 2, 1);
        System.out.println(crc.getValue());
    }

}
