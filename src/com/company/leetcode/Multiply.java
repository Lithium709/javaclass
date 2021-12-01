package com.company.leetcode;

public class Multiply {

    private static  String add(String num1, String num2){
        int i=num1.length()-1;
        int j=num2.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i>=0||j>=0){
            int a =0;
            int b =0;
            if(i>=0){
                a = num1.charAt(i)-'0';
            }
            if(j>=0){
                b = num2.charAt(j)-'0';
            }
            int s = a + b + carry;
            sb.append('0'+(s%10));
            carry = s/10;
            i--;
            j--;
        }
        return sb.reverse().toString();
    }


    public static  String multiply(String num1, String num2) {

        String s = "0";
        for(int i=num1.length()-1; i>=0;i--){
            StringBuilder sb = new StringBuilder();
            int carry=0;
            int a = num1.charAt(i)-'0';
            for(int j=num2.length()-1;j>=0;j--){
                int b = num2.charAt(j)-'0';
                int m = a*b+carry;
                sb.append(m);
                carry = m/10;
            }
            for(int k=0;k<num1.length()-i-1;k++){
                sb.append('0');
            }
            String sum = sb.toString();
            s = sum;
        }

        return s;

    }

    public static void main(String[] args) {

        System.out.println(multiply("2","3"));

    }
}
