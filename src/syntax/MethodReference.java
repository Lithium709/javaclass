package syntax;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

interface I<T1, T2, R>{
    R m1(T1 t1, T2 t2);
}

class A{
   public Integer m1(String o) {
        System.out.println("m1");
        return null;
    }
}

public class MethodReference {

    void run(I i){

    }

    public static void process(I<A, String, Integer> i){
        i.m1(new A(), "ww");
    }

    public static void main(String[] args){

       A a = new A();
       process(A::m1);
       Integer[] arr = {7,9,1,2,4,5,6};
       //String
       //Arrays.sort(arr,A::m1);
        // Arrays.sort(arr, Integer::compare);

        List<Integer> list = Arrays.asList(arr);
        int max = list.stream().max((v1,v2)->v1-v2).get();

        System.out.println(max);

        String[] stringArray = { "Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        Predicate even = ( i)->(Integer)i%2==0;


    }
}
