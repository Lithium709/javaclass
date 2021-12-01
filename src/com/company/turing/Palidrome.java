package com.company.turing;

public class Palidrome {

    public static void main(String[] args) {

    }


    public static boolean isPalindrome(String word) {
        // Please write your code here.
        final String lowerCase = word.toLowerCase();
        final int length = lowerCase.length();
        for (int i = 0; i < length; i++) {
            if (lowerCase.charAt(i) != lowerCase.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

}
