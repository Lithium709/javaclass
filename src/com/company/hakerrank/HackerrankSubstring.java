package com.company.hakerrank;

public class HackerrankSubstring {

    public static void main(String[] args) {

        String s = "rhbaasdndfsdskgbfefdbrsdfhuyatrjtcrtyytktjjt";

        System.out.println(hackerrankInString(s) ? "YES" : "NO");


    }

    private static boolean hackerrankInString(String s) {
        String template = "hackerrank";
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == template.charAt(pos)) {
                ++pos;
                if (pos == template.length()) {
                    return true;
                }
            }
        }
        return false;
    }

}
