package syntax;


import java.nio.file.AccessDeniedException;
import java.util.function.IntFunction;

class Outer{

    int m1(){
        return Inner.i;
        //new C().new D();
    }

      class Inner{
           private final static   int i =0;
           final int r=9;
    }
    static class C {
        private class D{

        }
    }

}

public class InnerClasses
{
    public static void main(String[] args){

        //IntFunction
        // AccessDeniedException
    }
}

