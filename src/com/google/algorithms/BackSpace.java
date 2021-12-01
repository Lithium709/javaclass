package com.google.algorithms;

public class BackSpace {


    private static String process(String S) {
        StringBuilder sb = new StringBuilder();
        int toSkip = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if(c == '#') {
                toSkip++;
                continue;
            }
            if(toSkip == 0 ) {
                sb.append(c);
            }
            toSkip--;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static boolean backspaceCompare(String S, String T) {

        return process(S).equals(process(T));
    }

    public static void main(String[] args) {
        backspaceCompare("ab#c", "ad#c");

    }
}
