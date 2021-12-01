package com.company.leetcode;

public class LongestSubstring {

    public static  int lengthOfLongestSubstring(String s) {


        if(s.isEmpty()){
            return 0;
        }
        int[] counter = new int[Character.MAX_VALUE];
        int max = Integer.MIN_VALUE;
        int i=0, j=0;
        for(; j<s.length();j++){

            char c = s.charAt(j);
            if(counter[c]>0){
                max = Math.max(max, j-i);
                i=Math.max(counter[c],i);
                counter[c]=j+1;

            }
            else{
                counter[c]=j+1;
            }

        }
        max = Math.max(max, j-i);
        return max;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abba"));
    }

}
