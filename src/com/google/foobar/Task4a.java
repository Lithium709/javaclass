package com.google.foobar;

import java.util.HashSet;

public class Task4a {


    public static int sign(int i) {
        return i % 2 == 0 ? 1 : -1;
    }

    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {

        int width = dimensions[0];
        int height = dimensions[1];

        final int n = Math.max(distance / height, distance / width) + 1;

        HashSet<Long> angles = new HashSet<>();
        int count = 0;
        int[] signx = new int[]{1, 1, -1, -1};
        int[] signy = new int[]{1, -1, -1, 1};
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int s = 0; s < 2; s++) {
                    int sign = s == 0 ? -1 : 1;
                    if (i == 0 && j == 0 && sign == -1) {
                        continue;
                    }
                    int x0 = 2 * j * width + sign * your_position[0];
                    int y0 = 2 * i * height + sign * your_position[1];
                    int g0 = 2 * j * width + sign * guard_position[0];
                    int h0 = 2 * i * height + sign * guard_position[1];
                    for (int k = 0; k < 4; k++) {

                        int x = x0 * signx[k];
                        int y = y0 * signy[k];
                        int g = g0 * signx[k];
                        int h = h0 * signy[k];
                        final double d1 = (double) g - your_position[0];
                        final double d2 = (double) h - your_position[1];
                        final double c1 = (double) x - your_position[0];
                        final double c2 = (double) y - your_position[1];
                        double d = d1 * d1 + d2 * d2;
                        if (d > distance * distance) {
                            continue;
                        }
                        final long a = key(c1, c2);
                        final long key = key(d1, d2);
                        final double c = c1 * c1 + c2 * c2;
                        if (key == a && d > c && c > 0) {
                            angles.add(a);
                            continue;
                        }

                        //  System.out.println("C=(" + x + "," + y + "," + Math.sqrt(c1 * c1 + c2 * c2) + "," + key(c1, c2) + ")");

                        //&& key==a &&

                        if (!angles.add(key)) {
                            angles.add(a);
                            continue;
                        }
                        angles.add(a);
                        System.out.println("(" + g + "," + h + "," + Math.sqrt(d) + "," + key + ")");

                        count++;
                    }
                }
            }
        }

        return count;

    }

    private static long key(double c1, double c2) {
        return Math.round(Math.atan2(c1, c2) * 10000);
    }

    public static void main(String[] args) {

        //    System.out.println(solution(new int[]{3, 2}, new int[]{1, 1}, new int[]{2, 1}, 4)); // expected 7
        // System.out.println(solution(new int[]{10, 10}, new int[]{4, 4}, new int[]{3, 3}, 5000)); // 739323
        // System.out.println(solution(new int[]{23, 10}, new int[]{6, 4}, new int[]{3, 2}, 23)); //8
        //   System.out.println(solution(new int[]{300, 275}, new int[]{150, 150}, new int[]{185, 100}, 500)); // 9
        System.out.println(solution(new int[]{2, 5}, new int[]{1, 2}, new int[]{1, 4}, 11)); // 27
    }

}
