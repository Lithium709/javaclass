package com.company.crossover;

import java.util.ArrayDeque;
import java.util.Deque;

public class InterviewTask {

    public static void main(String[] args) {
       // input
        //  ((a))(b)


        // output
        // (a)
        // ((a))
        // (b)
        showClosed("((a))(b)");
    }

    private static void showClosed(String input){

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)=='('){
                stack.push(i);
            }
            if(input.charAt(i)==')'){
                int pos = stack.pop();
                System.out.println(input.substring(pos, i+1));
            }
        }
    }

}
