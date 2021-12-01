package com.google.foobar;

import java.util.Arrays;

public class Task22 {

    static int[] f = new int[100];

    static int fib(int n) {
        Arrays.fill(f, 0);
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            final int i = f[n] = 1;
            return i;
        }

        if (f[n] == 1) {
            return f[n];
        }
        int k;
        if ((n & 1) == 1) {
            k = (n + 1) / 2;
        } else {
            k = n / 2;
        }

        if ((n & 1) == 1) {
            f[n] = (fib(k) * fib(k) +
                    fib(k - 1) * fib(k - 1));
        } else {
            f[n] = (2 * fib(k - 1) + fib(k)) * fib(k);
        }

        return f[n];
    }

    public static int solution(int total_lambs) {

        final int generous_n = 31 - Integer.numberOfLeadingZeros(total_lambs + 1);

        int stingy_n = generous_n;
        while (true) {
            int sum_fib = fib(stingy_n + 2) - 1;
            if (sum_fib > total_lambs) {
                break;
            }
            stingy_n++;
        }

        return stingy_n - generous_n - 1;
    }


    public static void main(String[] args) {

        // double phi = (Math.sqrt(5) + 1) / 2;

        // System.out.println((Math.pow(phi, 3) - Math.pow(phi - 2, 3) / (2 * phi + 1)));

        System.out.println(solution(143));
        System.out.println(solution(10));

    }
}
