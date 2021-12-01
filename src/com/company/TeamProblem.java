package com.company;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.*;
public class TeamProblem {


    static int[] acmTeam(int n, int m, String[] topic) {
        BitSet[] parts = (BitSet[]) Arrays.stream(topic).map(x->{
            BitSet b = new BitSet(m);
            for(int i=0; i<m;i++){
                if(x.charAt(i)=='1') b.set(i);
            }
            return b;
        }).toArray();
        int[] res = new int[2];
        Map<String, Integer> teams = new HashMap<>();

        for(int i=0; i<topic.length;i++){
            for(int j =0; j<i;j++){
                BitSet clone= (BitSet)parts[i].clone();
                clone.or(parts[j]);
                teams.put("(" + i + ","+ j + ")", clone.cardinality());
            }
        }
        Map<Integer,Long>  r = teams.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue()
                        .reversed())
                .mapToInt(x->x.getValue()).mapToObj(x->x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        TreeMap<Integer, Long> treeMap = new TreeMap<Integer, Long>();
        treeMap.putAll(r);
        res[0] =  treeMap.lastKey();
        res[1] = treeMap.lastEntry().getValue().intValue();
        return res;
    }

}
