package com.google.foobar;

public class Task1 {


    // method to print the divisors
    static int findMaxPieces(String x) {
        int n = x.length();
        for (int i = n; i >= 2; i--) {
            if (n % i == 0) {

                String pattern = x.substring(0, n/i);
                boolean equals = true;
                for (int j = n/i; j < n; j += n / i) {
                    equals = equals && pattern.equals(x.substring(j, j + n / i));
                    if (!equals){
                        break;
                    }
                }
                if (equals){
                    return i;
                }

            }
        }
        return 1;
    }

    public static void main(String[] args) {

      System.out.println(  findMaxPieces("abcabcabcabc"));
        System.out.println(  findMaxPieces("abccbaabccba"));

    }


}
