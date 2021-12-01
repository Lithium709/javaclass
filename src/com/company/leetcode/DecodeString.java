package com.company.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class DecodeString {


    public static String decodeString(String s) {

        String out =  "";

        Deque<String> stack_num = new ArrayDeque<>();
        Deque<String> stack_str = new ArrayDeque<>();
        String num="";
        String str="";
        for(int i=0; i< s.length();i++){

            char c = s.charAt(i);

            if(c>='0' && c <='9'){
                num+=c;
                if(!str.isEmpty()){
                    stack_str.push(str);
                    str="";
                }
            }

            if(c=='['){
                stack_num.push(num);
                num="";
            }


            if((c>='a' && c <='z') || (c>='A' && c <='Z')){
                str+=c;
            }

            if(c==']'){
                String sss = str;
                if (!stack_str.isEmpty() && str.isEmpty()){
                    sss = stack_str.pop();
                }
                String sum = "";
                final String count = stack_num.pop();
                for(int k=0; k<Integer.parseInt(count);k++){
                    sum += sss;
                }
                if(!stack_str.isEmpty()){
                    stack_str.push(stack_str.pop()+sum);
                }else {
                    stack_str.push(sum);
                }
                if(stack_num.isEmpty()){
                    out+=stack_str.pop();
                }
                str="";
            }

        }


        while (!stack_str.isEmpty()){
            out+=stack_str.pop();
        }

        return out+str;

    }

    public static void main(String[] args) {
      //  System.out.println(decodeString("3[a]2[bc]"));
      //  System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("2[b4[F]c]"));
    }
}
