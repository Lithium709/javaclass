package com.company.crossover.test;

public class gsd {


    private static int first(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return second(b, a - b);
    }

    private static int second(int a, int b) {
        if (a == 0) {
            return 0;
        }
        return first(b, a);
    }

    public static void main(String[] args) {
        System.out.println(first(5,3));
    }
}
