package com.company;

import java.util.HashMap;
import java.util.Map;

public class Ranson {


    public static void main(String[] args) {
       String magazine[] = {"give", "me", "one", "grand", "today", "night", "night"};
       String ransom[] = {"give", "me", "one", "grand", "today"};
       Map<String, Integer> tableSource = new HashMap<>();
       Map<String, Integer> tableDest = new HashMap<>();
        for(String k: magazine){
            tableSource.merge(k, 1,(v1,v2)-> v1+v2);
        }
        for(String k: ransom){
            tableDest.merge(k, 1,(v1,v2)-> v1+v2);
        }

        ;
        System.out.println(tableSource.computeIfAbsent("grand", x->0));
    }
}
