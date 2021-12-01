package com.google.algorithms;

public class DecodeString {

    static int  index = 0;

    static String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index))) {
                result.append(s.charAt(index++));
            } else {
                int k = 0;
                // build k until next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    k = k * 10 + s.charAt(index++) - '0';
                }
                // ignore the opening bracket '['
                index++;
                String decodedString = decodeString(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0) {
                    result.append(decodedString);
                }
            }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println(decodeString("2[c]"));

        int m = Integer.MIN_VALUE;
        int n = Integer.MAX_VALUE;

        System.out.println(-m);
        System.out.println(n);

    }
}
