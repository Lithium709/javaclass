package com.company.hakerrank.decibinary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution3 {


    static long dp[][] = new long[100][10000];

    static long countComb(int d, long s) {

        if (d == 0 && s == 0) {
            return 1;
        }

        if (d <= 0 || s <= 0) {
            return 0;
        }

        long count = 0;
        String number = "";
        for (int i = 0; i <= 9; i++) {
            dp[d][(int)s] += countComb(d - 1, s - i * (1 << (d - 1)));
        }
        if (!number.equalsIgnoreCase("0")){
            System.out.println(number);
        }
        return count;

    }


    static long decBydeciOrder(long x) {
        long sum = 0;
        long count = 0;
        for (long i = 0; i <= x; i++) {

            final long dc = countComb(4, i);
            sum += dc;
            if (sum >= x) {
                break;
            }
            count++;
        }
        return count;
    }


    // Complete the decibinaryNumbers function below.
    static long decibinaryNumbers(long x) {

        // decimal!
        final long dec = decBydeciOrder(x);
        long i = x - 1;
        while (dec == decBydeciOrder(i)) {
            i--;
        }
        long start = i == x - 1 ? x : i + 1;
        return start;
    }


    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        //     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            long x = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long result = decibinaryNumbers(x);

            System.out.println(String.valueOf(result));
        }

        // bufferedWriter.close();

        scanner.close();

      //  Arrays.binarySearch()
    }




/*
    public static void main(String[] args) {

        System.out.println(countComb(10, 24));


    }
*/
}
