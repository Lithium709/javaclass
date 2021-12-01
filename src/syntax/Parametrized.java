package syntax;

import java.util.*;

class X {}
class Y extends X { }
class Z extends Y { }

class D<Z>{

}
public class Parametrized {

    public static <E extends CharSequence> List<? extends E> doIt(List<E> list){

        return list;

    }

    <T> T method1(List<? extends T> list) {
        return list.get(0);
    }

    void method2(List<? super Double> list) {
         list.add(Math.PI);
    }

    public static void main(String[] args){


        List<? super Number> ll = new ArrayList<>();
        List<Double> lll = new ArrayList<>();
        new Parametrized().method2(ll);
        new Parametrized().method2(lll);

       List<StringBuilder> r1 = new ArrayList<>();
       r1.add(new StringBuilder("s"));
       List<String> r2 = new ArrayList<>();
       r2.add(new String("s"));
       ArrayList<String> r3 = new ArrayList<>();
       r2.add(new String("s"));
       List<? extends String> l = doIt(r3);
       //l.add("iui");
       List l2=doIt(r3);

       //List<? super Object> ll = new ArrayList<>();
      // ll.add("4343");
       //ll.add(new Object());

        CharSequence sb = new Parametrized().method1(r1);


       List<?> list1 = new ArrayList<X>();
       List<? extends Z> list2 = new ArrayList<>();

      List<? super Z> list3 = new ArrayList<Z>();


        Map<String, ? extends Number> hm = new HashMap<String, Integer>();
      //  list3.add(new Z());

        Set<Number> numbers = new HashSet<>();
        numbers.add(new Integer(86));
        numbers.add(75);
        numbers.add(new Integer(86));
        numbers.add(null);
        numbers.add(309L);
        Iterator iter = numbers.iterator();
        while (iter.hasNext())
            System.out.print(iter.next());

        // Vector
        // java.io.FileNotFoundException

    }
}
