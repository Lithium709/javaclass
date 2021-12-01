package com.company;

import java.util.Arrays;

public class ProductNoDivision {

    public static void main(String[] a) {
       //            3*5 2*5 2*3
        int[] arr = {2,3,5};
        for(int i: product(arr))System.out.println(i);
        System.out.println(Arrays.equals(product(arr), new int[]{15, 10, 6}));

    }

    static int[] product(int[] arr){

        //         2       2*3      2*3*5

        //         2*3*5   3*5       5
        int[] prodarr1 = new int[arr.length];
        int  prod = 1;
        for(int i=0;i<arr.length;i++){
            prodarr1[i]=prod;
            prod *= arr[i];
        }
        prod=1;
        int[] res = new int[arr.length];
        int[] prodarr2 = new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
            prodarr2[i]=prod*prodarr1[i];
            prod *= arr[i];
         }

        return prodarr2;

    }

}
