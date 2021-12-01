package syntax;

import java.io.File;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Predicate;

public class Student implements Comparable<Student>{
    private String name;
    private int id;

    @Override
    public int compareTo(Student o) {
        return this.id - o.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void debug(){
        System.out.println(id+" "+name);
    }

    public static void main(String...args){

        List<Student> slist = Arrays.asList(new Student("S1", 40), new Student("S2", 35), new Student("S3", 30));
        Predicate<Integer> p1 = x->x%2==0;
        Predicate p2         = x->(Integer)x%2==0;
        Predicate<Object> p3 = x->(Integer)x%2==0;
        Predicate<Integer> p4 = (Integer x)->x%2==0;

        Collections.sort(slist);
        slist.stream().forEach(Student::debug);

        Map m = new TreeMap();
        //m.put("2",24);
        m.put(1,24);
        m.put(3,24);

        File f =   new File("/a/b/c");
        System.out.println(f.exists());
        System.out.println(f.mkdirs());

        Future<?> future = null;
       // future.cancel()

    }
}
