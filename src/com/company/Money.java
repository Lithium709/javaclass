package com.company;

import java.time.Instant;

public class Money {

    public static void main(String[] a){

        int n = 10;
        int [] c = {2,5,3,6};
        long k = 1 << c.length;
        while (--k>0){
            int total = 0;
            for(int i=0;i<c.length;i++){
               total+= c[i] * ((k & (1 << i))>>i);
            }
            if(total>n) continue;

            System.out.println(Long.toBinaryString(k) + " " + total);
        }

    }

}
