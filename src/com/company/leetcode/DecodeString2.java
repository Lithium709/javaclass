package com.company.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodeString2 {

    static Pattern  pattern = Pattern.compile("([a-zA-Z]*)(\\d+)\\[(.*)\\](.*)");

    public static String decodeString(String s) {


        Matcher matcher = pattern.matcher(s);
        String out = s;
        if(matcher.find()){
            out = matcher.group(1);
            final int count = Integer.parseInt(matcher.group(2));
            for (int i = 0; i < count; i++) {
                out += decodeString(matcher.group(3));
            }
            out += decodeString(matcher.group(4));
        }

        return out;
    }

    public static void main(String[] args) {
      //  System.out.println(decodeString("3[a]2[bc]"));
      //  System.out.println(decodeString("3[a2[c]]"));
      //  System.out.println(decodeString("2[b4[F]c]"));
        System.out.println(decodeString("3[a]2[bc]"));
    }
}
