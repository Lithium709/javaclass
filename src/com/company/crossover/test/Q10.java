package com.company.crossover.test;

public class Q10 {


    static int  x =0;

    static void incX() {
        for (int i = 0; i < 10000; i++) {
            x = x + 1;
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {

            new Thread(()-> incX()).start();

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(x);

    }

}
