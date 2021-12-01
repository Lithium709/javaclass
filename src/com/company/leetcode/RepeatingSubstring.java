package com.company.leetcode;

public class RepeatingSubstring {

    public static boolean repeatedSubstringPattern(String s) {

        int n = s.length();
        for (int i = 0; i < n - 1; i++) {

            //012345
            //ababab
            if (n % (i + 1) == 0) {
                String sub0 = s.substring(0, i + 1);
                boolean allmatch = true;
                for (int k = 0; k < n / (i + 1) && i + 1 + k * (i + 1) + i + 1 <= n; k++) {
                    String sub = s.substring(i + 1 + k * (i + 1), i + 1 + k * (i + 1) + i + 1);
                    if (!sub.equals(sub0)) {
                        allmatch = false;
                        break;
                    }
                }
                if (allmatch) {
                    return true;
                }

            }
        }
        return false;

    }

    public static void main(String[] args) {

        System.out.println(repeatedSubstringPattern("abab"));

    }

}
