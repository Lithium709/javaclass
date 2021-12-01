package com.company.leetcode;

public class DecodeString4 {

    // D(abc)  -> abc
    // D(2[a]) -> aa
    // D(2[D(a)])
    // D(3[ab]2[cd]) > D(3[ab]) + D(2[cd])
    // D()

    private String decodeString(char[] s, int index) {
        StringBuilder result = new StringBuilder();
        while (index < s.length && s[index] != ']') {
            if (!Character.isDigit(s[index])) {
                result.append(s[index++]);
            } else {
                int k = 0;
                while (Character.isDigit(s[index])) {
                    k *= 10;
                    k += Character.getNumericValue(s[index++]);
                }
                index++;
                String decodedString = decodeString(s, index);
                index++;
                while (k-- > 0) {
                    result.append(decodedString);
                }
            }
        }
        return result.toString();
    }

    private long lengthString(char[] s , int index) {
        long length = 0;
        while (index < s.length && s[index] != ']') {
            if (!Character.isDigit(s[index])) {
                while(Character.isAlphabetic(s[index++])) {
                    length++;
                }
            } else {
                int k = 0;
                while (Character.isDigit(s[index])) {
                    k *= 10;
                    k += Character.getNumericValue(s[index++]);
                }
                index++;
                long len = lengthString(s, index);
                index++;
                length += len * k;
            }
        }
        return length;
    }

    public String decodeString(String s) {
        long buffer_length = lengthOfString(s);
        char[] buffer = new char[(int)buffer_length];
        return decodeString(s.toCharArray(), 0);
    }

    public long lengthOfString(String s) {
        return  lengthString(s.toCharArray(), 0);
    }

    public static void main(String[] args) {
        final long len = new DecodeString4().lengthOfString("1000[1000[1000[1000[a]]]");
        final String string = new DecodeString4().decodeString("10[a");
        System.out.println(len);
        System.out.println(string);
    }
}
