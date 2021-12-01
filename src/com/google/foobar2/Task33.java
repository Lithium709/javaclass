package com.google.foobar2;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/*
Fuel Injection Perfection
=========================

Commander Lambda has asked for your help to refine the automatic quantum antimatter fuel injection system for her LAMBCHOP doomsday device. It's a great chance for you to get a closer look at the LAMBCHOP - and maybe sneak in a bit of sabotage while you're at it - so you took the job gladly.

Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a time. However, minions dump pellets in bulk into the fuel intake. You need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time.

The fuel control mechanisms have three operations:

1) Add one fuel pellet
2) Remove one fuel pellet
3) Divide the entire group of fuel pellets by 2 (due to the destructive energy released when a quantum antimatter pellet is cut in half, the safety controls will only allow this to happen if there is an even number of pellets)

Write a function called solution(n) which takes a positive integer as a string and returns the minimum number of operations needed to transform the number of pellets to 1. The fuel intake control panel can only display a number up to 309 digits long, so there won't ever be more pellets than you can express in that many digits.

For example:
solution(4) returns 2: 4 -> 2 -> 1
solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit Solution.java

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Python cases --
Input:
solution.solution('15')
Output:
    5

Input:
solution.solution('4')
Output:
    2

-- Java cases --
Input:
Solution.solution('4')
Output:
    2

Input:
Solution.solution('15')
Output:
    5
 */

public class Task33 {

    /*
        private static int count(BigInteger i, int count) {

            if (i.equals(BigInteger.ONE)) {
                return count;
            }

            if (i.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                return count(i.divide(BigInteger.valueOf(2)), count + 1);
            } else {

                int plusOne = count(i.add(BigInteger.ONE), count + 1);
                int minusOne = count(i.subtract(BigInteger.ONE), count + 1);
                return Math.min(plusOne, minusOne);
            }

        }
    */

    public static int solution(String s) {

        int c = 0;

        BigInteger current = new BigInteger(s);

        while (current.compareTo(BigInteger.ONE) > 0) {

            if (current.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                current = current.divide(BigInteger.valueOf(2));
                c++;
            } else {

                BigInteger a = current.add(BigInteger.ONE);
                BigInteger b = current.subtract(BigInteger.ONE);
                int ca = 1;
                int cb = 1;
                while (a.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    a = a.divide(BigInteger.valueOf(2));
                    ca++;
                }
                while (b.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    b = b.divide(BigInteger.valueOf(2));
                    cb++;
                }

                if (ca > cb && !current.equals(BigInteger.valueOf(3))) {
                    current = a;
                    c += ca;
                } else {
                    current = b;
                    c += cb;
                }
            }

        }
        return c;
    }

    public static void main(String[] args) {

        assert solution("4") == 2;
        assert solution("15") == 5;
        assert solution("2") == 1;
        assert solution("3") == 2;
        assert solution("5") == 3;
        assert solution("1024") == 10;

        //assert solution("23243423545") == 3;
        String s = "73123712928719731927391739179172973917239712937192739791739122993939930492034039039024928197273187310121301832839127381723783948923859479803841028975894491834384975483284923747584982332040234927581043949375350185397519349137519401839743147918403184937591750184937927401893747239104803849375821824032974293574";
        System.out.println(solution(s));
    }

}
