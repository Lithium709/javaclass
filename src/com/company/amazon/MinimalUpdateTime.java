package com.company.amazon;

import java.util.LinkedList;
import java.util.Queue;

/*

Updated server is depicted as 1, otherwise 0
An update is uploaded to adjacent server (up down left right - non diagonal).
it takes 1 day to update.
write a function that return minimal time required to update all servers.

    00100000
    10010000
    00000000
    00000000
*/
public class MinimalUpdateTime {

    // To store matrix cell coordinates
    static class Point {

        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }


    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    private static boolean isValid(int x, int y, int n) {
        return x < n && y < n && x >= 0 && y >= 0;
    }

    public static int minimalTime(int[][] m) {

        int n = m.length;
        int dist = 0;
        boolean[][] visited = new boolean[n][n];
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m[i][j] == 1) {
                    q.add(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        if (q.isEmpty()) {
            return -1;
        }
        while (!q.isEmpty()) {
            Point e = q.remove();
            dist = Math.max(dist, e.dist);
            for (int i = 0; i < 4; i++) {
                int row = e.x + rowNum[i];
                int col = e.y + colNum[i];
                if (isValid(row, col, n) && !visited[row][col]) {
                    visited[row][col] = true;
                    q.add(new Point(row, col, e.dist + 1));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        System.out.println(minimalTime(
                new int[][]{
                        {1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1}}));
    /*
        System.out.println(minimalTime(
                new int[][]{
                        {0, 1, 0},
                        {0, 0, 0},
                        {0, 0, 0},
                }));

         */
    }


}
