package com.company.hakerrank.decibinary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution2 {


    // Complete the decibinaryNumbers function below.

    static List<List<Long>> map = new ArrayList<>(500);

    static {
        long counter = 0L;

        // 10  2
        // 100 4
        // 1000 8
        // 10_000 16
        // 100_000 32
        // 1000_000 64
        long m = 0;
        while (counter<=100_000_000L){

            long k = counter;
            long value =counter;
            int key=0;
            int rk=1;
            while (k>0) //  12
            {
                key += rk*(k%10);
                k/=10;
                rk*=2;
            }

           if(key>0b100_000_000L){
                counter++;
                continue;
            }
            m++;

            if (map.size() > key) {
                map.get(key).add(value);
            } else {
                List<Long> list = new ArrayList<>(1024);
                list.add(value);
                map.add(key, list);
            }
            counter++;
        }
        System.out.println("total numbers :" + m);
    }

    // Complete the decibinaryNumbers function below.
    static long decibinaryNumbers2(long x) {

        long n = x;
        for (int k=0;k< map.size();k++) {

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
