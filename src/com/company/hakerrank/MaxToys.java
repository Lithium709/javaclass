package com.company.hakerrank;

import java.util.Arrays;

public class MaxToys {

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {

        Arrays.sort(prices);
        int i =0;
        while(k>0){
            k-=prices[i];
            if(k<0){
                break;
            }
            i++;
        }
        return i;

    }

    public static void main(String[] args) {

        int[] a = new int[]{1,2,3,4};

        System.out.println(maximumToys(a, 7));

    }


}

