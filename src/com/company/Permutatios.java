package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutatios {


    private static List<String> sub(String s, int i) {

        if (i == 0) {
            return Arrays.asList(s.substring(i, i + 1));
        }
        if (i == 1) {
            return Arrays.asList(s.substring(i, i + 1) + s.substring(i - 1, i),
                    s.substring(i - 1, i) + s.substring(i, i + 1));
        }

        List<String> list = new ArrayList<>();
        List<String> prev = sub(s, i - 1);
        for (String sub : prev) {
            for (int k = 0; k <= i; k++) {
                list.add(sub.substring(0, k) + s.charAt(i) + sub.substring(k));
            }
        }
        return list;
    }

    private static List<String> permute(String s) {

        //   List<String> res = new ArrayList<>();

        return sub(s, s.length() - 1);

    }


    public static void main(String[] args) {

        // P(a) - > a
        // P(ab) -> P(ab, ba)
        // abc -> insert c  -  cab acb abc  cba bca bac

        for (String s : permute("abc")) {
            System.out.println(s);
        }

    }


}
