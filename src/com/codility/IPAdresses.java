package com.codility;

public class IPAdresses {





    public  static  int solve(String s, int dots){

        int num = 0;

        if(dots == 0){
            if( s.length()>0 && Integer.parseInt(s)<=255)
                return 1;
            else
                return 0;
        }

        if((double)s.length()/(dots+1)>3){
            return 0;
        }

        if(s.length()>0){
            num += solve(s.substring(1),dots-1);
        }
        if(s.length()>1){
            num += solve(s.substring(2), dots-1);
        }
        if(s.length()>2){
            String sub = s.substring(0,3);
            if (Integer.parseInt(sub)<=255) {
                num += solve(s.substring(3),dots-1);
            }
        }

        return num;

    }


    public static void main(String[] args) {

        String s1 ="255255255255";

       // solve("255.255.255.255")  =>  comb(255) *  solve(255.255.255,2)  + solve(2) * solve(55255255255,2)

        System.out.println( solve(s1,3)); // 1
        String s2 ="188212";
        System.out.println( solve(s2,3)); // 8
        String s3 ="1111";
        System.out.println( solve(s3,3)); // 1
        String s4 = "12345";
        System.out.println( solve(s4,3)); // 4
        assert solve(s2,3)==8;
    }

}
