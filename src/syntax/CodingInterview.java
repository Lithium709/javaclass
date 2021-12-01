package syntax;

public class CodingInterview {


    public static void main(String[] args) {

        //  1234

        //  3456

        int a = 1234;

        int b = 3456;

        a = getNumberOfCarry(a, b);

        System.out.println(a%10);


    }

    private static int getNumberOfCarry(int a, int b) {
        int cs = 0;
        int r = 0;

        while (a>9 && b>9){

            a %= 10;
            b %= 10;
            int c = a + b + cs;

            if(c>9){
                r++;
                cs += c - 10;
            }

        }
        return a;
    }


}
