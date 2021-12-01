package com.google.foobar;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task5 {


    private static int[][] buildGCDTable(int n) {

        int[][] res = new int[n][n];

        for (int i = 0; i < res.length; i++) {
            for (int j = i; j < res.length; j++) {
                if (i == 0) {
                    res[i][j] = 1;
                    res[j][i] = 1;
                } else if (i == j) {
                    res[i][j] = i + 1;
                } else {
                    res[i][j] = res[i][j - i - 1];
                    res[j][i] = res[i][j - i - 1];
                }
            }
        }
        return res;
    }

    private static int gcd(int x, int y, int[][] gcdTable) {
        return gcdTable[x - 1][y - 1];
    }

    private static BigInteger[] buildFactorialTable(int n) {

        BigInteger[] res = new BigInteger[n];
        res[0] = BigInteger.ONE;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1].multiply(BigInteger.valueOf(i).add(BigInteger.ONE));
        }
        return res;
    }

    private static BigInteger factorial(int n, BigInteger[] factorialTable) {
        return factorialTable[n - 1];
    }

    private static BigInteger power(long a, long b) {

        if (b == 0) {
            return BigInteger.ONE;
        }
        final BigInteger multiplier = BigInteger.valueOf(a);
        BigInteger res = multiplier;
        for (int i = 1; i < b; i++) {
            res = res.multiply(multiplier);
        }
        return res;
    }

    private static BigInteger coefficientFactor(int[] c, int n, BigInteger[] factorialTable) {
        BigInteger nfact = factorial(n, factorialTable);

        int[] cc = new int[n + 1];
        for (int i = 0; i < c.length; i++) {
            cc[c[i]]++;
        }

        for (int i = 0; i < n + 1; i++) {
            if (cc[i] != 0) {
                nfact = nfact.divide(power(i, cc[i]).multiply(factorial(cc[i], factorialTable)));

            }
        }
        return nfact;
    }

    private static class PartitionCycle {

        BigInteger cycleCount;
        int[] partition;

        public PartitionCycle(BigInteger cycleCount, int[] partition) {
            this.cycleCount = cycleCount;
            this.partition = partition;
        }
    }

    public static List<PartitionCycle> partitionCycleCount(int n, BigInteger[] factorialTable) {
        List<PartitionCycle> result = new ArrayList<>();
        int[] a = new int[n + 1];
        int k = 1;
        int y = n - 1;
        while (k != 0) {
            int x = a[k-- - 1] + 1;
            while (2 * x <= y) {
                a[k++] = x;
                y -= x;
            }
            int l = k + 1;
            while (x <= y) {
                a[k] = x++;
                a[l] = y--;
                int[] partition = Arrays.copyOfRange(a, 0, k + 2);
                result.add(new PartitionCycle(coefficientFactor(partition, n, factorialTable), partition));
            }
            a[k] = x + y;
            y = x + y - 1;
            int[] partition = Arrays.copyOfRange(a, 0, k + 1);
            result.add(new PartitionCycle(coefficientFactor(partition, n, factorialTable), partition));
        }
        return result;
    }

    public static String solution(int w, int h, int s) {

        int n = Math.max(w, h);
        BigInteger[] factorialTable = buildFactorialTable(n);
        final int[][] gcdTable = buildGCDTable(n);
        BigInteger grid = BigInteger.ZERO;
        for (PartitionCycle pcw : partitionCycleCount(w, factorialTable)) {
            for (PartitionCycle pch : partitionCycleCount(h, factorialTable)) {
                final BigInteger m = pcw.cycleCount.multiply(pch.cycleCount);
                long sum = 0;
                for (int i = 0; i < pch.partition.length; i++) {
                    for (int j = 0; j < pcw.partition.length; j++) {
                        sum += gcd(pch.partition[i], pcw.partition[j], gcdTable);
                    }
                }
                grid = grid.add(m.multiply(power(s, sum)));
            }
        }
        return grid.divide(factorial(w, factorialTable).multiply(factorial(h, factorialTable))).toString();
    }

    public static void main(String[] args) {

        System.out.println(solution(12,12,20));

    // System.out.println(power(2, 4));

    }

}
