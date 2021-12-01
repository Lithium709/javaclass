package com.google.foobar2;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static int[] solution(int area) {
        int a = area;
        List<Integer> result = new ArrayList<>();
        while (a>0){

            int r = new Double( Math.sqrt(a)).intValue();
            result.add(r*r);
            a-=r*r;

        }

       int res[] = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            res[i]=result.get(i);
        }

        return res;

    }

    public static void main(String[] args) {
        for (int i:solution(15324)) {
            System.out.println(i);
        }
    }
}
