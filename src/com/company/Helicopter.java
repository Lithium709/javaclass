package com.company;

public class Helicopter {

    static boolean testEnergy(int[][] points, int initial) {
        int z = initial;
        System.out.println(z);
        for (int i = 1; i < points.length; i++) {
            int h = points[i][2];
            z += points[i - 1][2] - points[i][2];
            System.out.println(z);
            if (z < 0) {
                return false;
            }
        }
        return true;
    }

    static int minimunEnergy(int[][] points) {
        // int points[0][2];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            max = Math.max(max, points[i][2]);
        }
        return max - points[0][2];
    }

    public static void main(String[] a) {

        int[][] points1 = {
                {10, 4, 10}, // 0
                {6, 5, 0}, // 10
                {2, 3, 6}, // 4
                {2, 3, 1}, // 9
                {2, 3, 15},// -5
                {2, 3, 35},// 5
                {2, 3, 10},// 5
        };

        int[][] points2 = {
                {10, 4, 3}, // 0
                {6, 5, 0}, // 10
                {2, 3, 6}, // 4
                {2, 3, 11}, // 9
                {2, 3, 15},// -5
                {2, 3, 3},// 5
                {2, 3, 4},// 5
        };

        int min1 = minimunEnergy(points1);
        System.out.println("min = " + min1);
        System.out.println(testEnergy(points1, min1));
        int min2 = minimunEnergy(points2);
        System.out.println("min = " + min2);
        System.out.println(testEnergy(points2, min2));
    }

}
