package syntax;

import java.util.Arrays;

public class MinimalSwaps {

    public static void main(String[] args) {

        int[] a = {7, 1, 3, 2, 4, 5, 6};

        System.out.println(swaps(a));

    }


    static int swaps(int[]a){

        int len = a.length;

        if(len==1){
            return 0;
        }
        int swaps = 0;
        for(int j=1; j<len; j++){
            for(int i=j; i<len; i++) {

                if (a[i - 1] > a[i]) {
                    int tmp = a[i];
                    a[i] = a[i - 1];
                    a[i - 1] = tmp;
                    swaps++;
                }
            }
        }
        System.out.println( Arrays.toString(a));
        return swaps;
    }

}


