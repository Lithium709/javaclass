package com.company.hakerrank.decibinary;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {


    // Complete the decibinaryNumbers function below.
    static long decibinaryNumbers(long x) {

        Map<Long, List<Long>> map = new TreeMap<>();

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    for (int l = 0; l <= 9; l++) {
                        for (int m = 0; m <= 9; m++) {
                            for (int n = 0; n <= 9; n++) {
                                final long key = 32 * i + 16 * j + 8 * k + 4 * l + 2 * m + n;
                                final long value = 100_000 * i + 100_00 * j + k * 1000 + 100 * l + 10 * m + n;
                                if (map.get(key) != null) {
                                    map.get(key).add(value);
                                } else {
                                    List<Long> list = new ArrayList<>();
                                    list.add(value);
                                    map.put(key, list);
                                }
                            }
                        }
                    }
                }
            }
        }



        long n = x;
        for (long k: map.keySet()) {

            final List<Long> longs = map.get(k);
            if(n>longs.size()){
                n-=longs.size();
            }
            else{
                return longs.get((int)n-1);
            }

        }

        return 0;
    }

    static Map<Long, List<Long>> map = new TreeMap<>();

    static {
        long counter = 0L;

        // 10  2
        // 100 4
        // 1000 8
        // 10_000 16
        // 100_000 32
        // 1000_000 64
        long m = 0;
        while (counter<=1000_000_000L){

            long k = counter;
            long value =counter;
            long key=0;
            int rk=1;
            while (k>0) //  12
            {
                key += rk*(k%10);
                k/=10;
                rk*=2;
            }

           if(key>0b1000_000_000L){
                counter++;
                continue;
            }
            m++;

            if (map.get(key) != null) {
                map.get(key).add(value);
            } else {
                List<Long> list = new ArrayList<>();
                list.add(value);
                map.put(key, list);
            }
            counter++;
        }
        System.out.println("total numbers :" + m);
    }

    // Complete the decibinaryNumbers function below.
    static long decibinaryNumbers2(long x) {

        long n = x;
        for (long k: map.keySet()) {

            final List<Long> longs = map.get(k);
            if(n>longs.size()){
                n-=longs.size();
            }
            else{
                return longs.get((int)n-1);
            }

        }

        return 0;
    }


    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        //     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            long x = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long result = decibinaryNumbers2(x);

            System.out.println(String.valueOf(result));
        }

        // bufferedWriter.close();

        scanner.close();
    }

/*
    public static void main(String[] args) {

        System.out.println(countComb("1000"));


    }
*/
}
