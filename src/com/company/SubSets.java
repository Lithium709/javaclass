package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubSets {


    static String timeInWords(int h, int m) {

        Map<Integer,String> numbers = new HashMap<>();
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");
        numbers.put(4, "four");
        numbers.put(5, "five");
        numbers.put(6, "six");
        numbers.put(7, "seven");
        numbers.put(8, "eight");
        numbers.put(9, "nine");
        numbers.put(10, "ten");
        numbers.put(11, "eleven");
        numbers.put(12, "twelve");
        numbers.put(13, "thirteen");
        numbers.put(14, "fourteen");
        numbers.put(16, "sixteen");
        numbers.put(17, "seventeen");
        numbers.put(18, "eighteen");
        numbers.put(19, "nineteen");
        numbers.put(20, "twenty");
        if(m==0){
            return numbers.get(h) + " o'clock";
        }
        else if(m==15){
            return  "quarter past " + numbers.get(h);
        }
        else if(m==30){
            return  "half past " + numbers.get(h);
        }
        else if(m==45){
            return  "quarter to " + numbers.get(h+1);
        }
        else if (m<30){
            if(m==1){
                return  "one minute past" + numbers.get(h);
            }
            else{
                if(m>20){
                    return  numbers.get(20) + " " + numbers.get(m-20) + " minutes past " + numbers.get(h);
                }
                else
                {
                    return  numbers.get(m) + " minutes past " + numbers.get(h);
                }
            }
        }
        else {
            int n = 60-m;
            if(n==1){
                return  "one minute to" + numbers.get(h+1);
            }
            else{
                if(n>20){
                    return  numbers.get(20) + " " + numbers.get(n-20) + " minutes to " + numbers.get(h+1);
                }
                else
                {
                    return  numbers.get(n) + " minutes to " + numbers.get(h+1);
                }
            }
        }



    }


    public static void main(String[]args){

            String s = "Abraham";

        System.out.println(s.substring(1));

    }

}
