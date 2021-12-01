package com.company.crossover;

import java.util.Scanner;
public class BiggestContinousArray {

    /**
     * Complete the function below. Do not forget to include required imports.
     * DONOT MODIFY anything outside this function!
     */
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }

    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //int[] mainArray = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::valueOf).toArray();
        int subArraySum = maxSubArraySum(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(subArraySum);
    }

}
