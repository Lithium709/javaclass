package com.company.crossover.test;

public class Ink {


    public static void main(String[] args) {
        int[]i={1};
        Ink in = new Ink();
        in.increment(i);
        System.out.println(i[i.length-1]);
    }

    void increment(int[]i){
        i[i.length-1]++;
    }


}
