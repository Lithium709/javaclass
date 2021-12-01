package com.company.leetcode;


public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] a = nums1;
        int[] b = nums2;
        if (a.length > b.length) {
            a = nums2;
            b = nums1;
        }

        // x1, x2,| x3, x4
        // y1, y2, y3,| y4, y5, y6

        // x3>y3
        // y4>x2

        //  0   1
        // x1(1) | x2 (2)
        // y1(-1) | y2 (3)

        // 4,5
        // 1,2,3

        int m = a.length; //1
        int n = b.length;  //2

        int lo = 0;
        int hi = m;

        while (hi >= lo) {

            int pa = (hi + lo) / 2;
            int pb = (n + m + 1) / 2 - pa;

            if (pa < hi && pb > 0 && a[pa] < b[pb - 1]) {
                lo = pa + 1;
            } else if (pa > 0 && a[pa - 1] > b[pb]) {
                hi = pa - 1;  // 0
            } else {

                int MaxLeft = 0;
                if (pa == 0) {
                    MaxLeft = b[pb - 1];
                } else if (pb == 0) {
                    MaxLeft = a[pa - 1];
                } else {
                    MaxLeft = Math.max(a[pa - 1], b[pb - 1]);
                }

                if ((n + m) % 2 == 1) {
                    return MaxLeft;
                }

                int MaxRight = 0;
                if (pa == m) {
                    MaxRight = b[pb];
                } else if (pb == n) {
                    MaxRight = a[pa];
                } else {

                    MaxRight = Math.min(a[pa], b[pb]);

                }

                System.out.println("pa=" + pa);
                System.out.println("pb=" + pb);
                System.out.println(MaxLeft);
                System.out.println(MaxRight);
                return (MaxLeft + MaxRight) / 2.0;

            }

        }

        return 0.0;

    }


    public static void main(String[] args) {
        //  System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{-1,3}));
        //  System.out.println(findMedianSortedArrays( new int[]{-1,3}, new int[]{1,2}));

        System.out.println(findMedianSortedArrays(new int[]{4, 5}, new int[]{1, 2, 3}));
    }

}
