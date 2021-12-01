package com.codility;

import java.util.Arrays;

public class BS {

    public static void main(String[] args) {
        // 2  0  2   1 0
        final int[] a = {3, 1, 9, 6, 2};
        final int[] s = {3, 1, 9, 6, 2};  // must be dedupped

        Arrays.sort(s);
        int[] res = new int[5];
        for (int i = 0; i < a.length; i++) {

            final int bs = Arrays.binarySearch(s, a[i]);
            if (bs >= i) {
                res[i] = Math.min(bs, a.length - i - 1);
            }

            System.out.println(bs);

        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }

    }

}
