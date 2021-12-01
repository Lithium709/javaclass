package com.company.hakerrank;

public class MarsExlopation {

    public static void main(String[] args) {

        String s = "SOSSOWSOS";

        int errors = 0;
        for (int i = 0; i < s.length(); i += 3) {
            errors += s.charAt(i) == 'S' ? 0 : 1;
            errors += s.charAt(i + 1) == 'O' ? 0 : 1;
            errors += s.charAt(i + 2) == 'S' ? 0 : 1;
        }
        System.out.println(errors);

    }

}
