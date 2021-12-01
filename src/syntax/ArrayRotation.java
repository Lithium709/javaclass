package syntax;

import java.util.Arrays;

public class ArrayRotation {

    public static void main(String[] args) {


        int[] a = {1,2,3};

        System.out.println(Arrays.toString(rotate(a,1)));
        System.out.println(Arrays.toString(rotate(a,2)));
        System.out.println(Arrays.toString(rotate(a,3)));

    }

    public static int[] rotate(int[] ar, int n){

        final int length = ar.length;
        int[] b = new int[length];

        for(int i = 0; i < length; i++){

            b[i] = ar[(i + n)%length];

        }

        return b;
    }

}
