package com.company.cracking;

public class SubstringPermutation {

    static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
            97,
            101};

    public static void main(String[] args) {

        String a = "abbc";
        String b = "cbabadcdabbcccbabbbcbacbbaac";
        System.out.println(permutations(a, b));

    }

    // O(a) + O(b)
    static int permutations(String a, String b) {

        long factor = getFactor(a);
        int p = 0;
        long windowFactor = getFactor(b.substring(0, a.length()));
        for (int i = a.length(); i < b.length(); i++) {
            if (factor == windowFactor) {
                ++p;
            }
            windowFactor /= primes[b.charAt(i - 4) - 'a'];
            windowFactor *= primes[b.charAt(i) - 'a'];

        }
        return p;
    }

    private static long getFactor(String window) {
        long mul = 1;
        for (int j = 0; j < window.length(); j++) {
            mul *= primes[window.charAt(j) - 'a'];
        }
        return mul;
    }

}
