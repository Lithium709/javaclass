package com.company.hakerrank;

public class SlidingMedian {


    static double getMedian(int[] index, int d) {

        if (d % 2 == 1) {
            int half = d / 2 + 1;
            int i = -1;
            while (half > 0) {
                if (index[++i] > 0) {
                    half -= index[i];
                }

            }
            return i;
        } else {

            int half = d / 2;
            int i = -1;
            while (half > 0) {
                if (index[++i] > 0) {
                    half -= index[i];
                }

            }
            double median1 = i;
            half++;
            while (half > 0) {
                if (index[++i] > 0) {
                    half -= index[i];
                }

            }
            double median2 = i;

            return (median1 + median2) / 2;

        }
    }

    static int activityNotifications(int[] expenditure, int d) {

        int count = 0;
        int[] index = new int[200];
        int i = 0;
        for (; i < d; i++) {
            index[expenditure[i]]++;
        }
        //  System.out.println(getMedian(index, d));

        for (; i < expenditure.length - 1; i++) {

            double median = getMedian(index, d);

            if (Math.abs(expenditure[i] - 2 * median) < 0.0001 || expenditure[i] > 2 * median) {
                count++;
            }

            index[expenditure[i - d]]--;
            index[expenditure[i]]++;
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(activityNotifications(new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 5));
        //  System.out.println(activityNotifications(new int[]{1, 2, 3, 4, 4}, 4));
        //  System.out.println(activityNotifications(new int[]{10, 20, 30, 40, 50}, 3));

    }

}
