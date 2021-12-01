package com.company.hakerrank;

public class TwoCharacters {


    public static void main(String[] args) {

        String s = "beabeefeab";

        System.out.println(alternate(s));

    }

    static int alternate(String s) {

        int[] stat = new int[26];

        for (int i = 0; i < s.length(); i++) {
            stat[s.charAt(i) - 'a']++;
        }
        int max = 0;
        for (int i = 0; i < 26; i++) {
            if (stat[i] > 0) {
                for (int j = i + 1; j < 26; j++) {
                    if (stat[j] > 0) {
                        if (isValid(leaveChars(s, (char) ('a' + i), (char) ('a' + j)))) {
                            max = Math.max(max, stat[i] + stat[j]);
                        }
                    }
                }
            }
        }

        return max;

    }

    static boolean isValid(String s) {
        char prev = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (prev == s.charAt(i)) {
                return false;
            }
            prev = s.charAt(i);
        }
        return true;
    }

    static String leaveChars(String s, char a, char b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == a || c == b) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
