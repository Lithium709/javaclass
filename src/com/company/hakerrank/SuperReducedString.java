package com.company.hakerrank;

public class SuperReducedString {


    public static void main(String[] args) {

        String s = "baab";

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                s = s.substring(0, i-1) + s.substring(i + 1);
                i = 0;
            }
        }

        System.out.println(s);
    }

}
