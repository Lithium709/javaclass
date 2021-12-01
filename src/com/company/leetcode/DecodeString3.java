package com.company.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString3 {


    public static String decodeString(String s) {

        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                String ss = "";
                while (!stack.isEmpty()) {
                    String d = stack.pop();
                    if (d.charAt(0) == '[') {
                        char count = stack.pop().charAt(0);
                        int r = 10;
                        while (!stack.isEmpty()) {
                            final char top = stack.peek().charAt(0);
                            if (!(top >= '0' && top <= '9'))
                                break;
                            count += (stack.pop().charAt(0) - '0') * r;
                            r *= 10;
                        }
                        StringBuilder sss = new StringBuilder("");
                        for (int j = 0; j < count - '0'; j++) {
                            sss.append(ss);
                        }
                        stack.push(sss.toString());
                        break;
                    }
                    ss = d + ss;
                }
            } else {
                stack.push("" + c);
            }
        }

        String out = "";
        while (!stack.isEmpty()) {
            out = stack.pop() + out;
        }
        return out;

    }

    public static void main(String[] args) {
        //  System.out.println(decodeString("3[a]2[bc]"));
        //  System.out.println(decodeString("3[a2[c]]"));
        // System.out.println(decodeString("2[b4[F]c]"));
        System.out.println(decodeString("100[leetcode]").length());
    }
}
