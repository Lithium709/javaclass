package com.company.crossover.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Q4 {

    static volatile Map<String, Integer> m;

    public synchronized  void update(String k, Integer v){

        if(m == null){
            m = new HashMap<>();
        }

        Integer pv = m.getOrDefault(k,0);
        m.put(k, pv+v);
    }

    public synchronized int calculateSum(){
        int sum = 0;
        if (m==null) return 0;
        for(Integer v: m.values()){
            sum +=v;
        }

        return sum;
    }


    public static void main(String[] args) {

        new Thread(()->{

            for (int i = 0; i < 10000; i++) {

                new Q4().calculateSum();
            }


        }).start();

        new Thread(()->{

            for (int i = 0; i < 1000; i++) {

                new Q4().update("a" +i,i);
            }


        }).start();





    }

}
