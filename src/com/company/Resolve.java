package com.company;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Resolve {
    public static void main(String[] a){
        Path p1 = Paths.get("a/b/c");
        Path p2 = Paths.get("d/e");

        System.out.println(p1.resolve(p2));
        System.out.println(p1.relativize(p2));
    }
}
