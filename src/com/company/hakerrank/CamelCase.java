package com.company.hakerrank;

public class CamelCase {


    public static void main(String[] args) {

        String s = "oneTwoThree";

        int c= 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >='A' && s.charAt(i) <='Z' ){
                c++;
            }
        }

    }

}
