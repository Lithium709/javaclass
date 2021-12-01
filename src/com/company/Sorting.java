package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Sorting {

    public static void main(String[] a){

        String[] ar = {"bb23", "BB23", "23bB", "b23B", "bB23", "B23b", "23Bb" };

        Arrays.sort(ar);
        for(String s: ar) System.out.println(s);

        System.out.println('B'-'b');


    }
}
