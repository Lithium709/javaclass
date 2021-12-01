package com.company.leetcode;


public class MinimalSpanningTree {

    static int[] id;

    static int n = 5;

    static void union(int v, int w) {
        // id[v] = w;
        System.out.println("Connect " + v + " and " + w);
        int vid = id[v];
        int wid = id[w];
        for(int i = 0; i < n; i++){
            if(id[i] == id[v]) {
                id[i] = id[w];
            }
        }
        for(int i: id) System.out.print(i+",");
        System.out.println();
    }


    public static void main(String[] args) {

        id = new int[n];
        for(int i = 0; i < n; i++){
            id[i] = i;
        }

        union(1,3);
        union(3,4);
        union(0,1);
        union(1,2);


    }

}
