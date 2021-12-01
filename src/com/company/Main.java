package com.company;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static int[] sortBubble(int[] array){
        int n = array.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                if(array[j]>array[j+1]){
                    swap(j,j+1,array);
                }
            }
        }
        return array;
    }

    static void swap(int i, int j, int[]array){
        int tmp = array[i];
        array[i]=array[j];
        array[j]= tmp;
    }


    public static void main(String[] args) {

        int[] a = {5,61,2,8,19,22,};

        List<Integer> list =  Arrays.stream(sortBubble(a)).mapToObj(x->x).collect(Collectors.toList());

        System.out.println(list);

        String s=null;
       // s.length()

        Integer l = 1;
        StringBuffer sb = new StringBuffer();
        sb.reverse();


    }
}
