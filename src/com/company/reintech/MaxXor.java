package com.company.reintech;

public class MaxXor {


    public static void main(String[] args) {

        maximizingXor(10,15);

    }

    static int maximizingXor(int l, int r) {

        int c = log(r)-1;
        while((r & (1<<c)) == (l & (1<<c))){
            c--;
        }
        return 1<<(c+1)-1;
    }

    private static int log(int x){
        int digits =1 ;
        while((x>>=1)!=0) ++digits;
        return digits;
    }
}
