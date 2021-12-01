package com.google.foobar2;

/*
Gearing Up for Destruction
==========================

As Commander Lambda's personal assistant, you've been assigned the task of configuring the LAMBCHOP doomsday device's axial orientation gears. It should be pretty simple - just add gears to create the appropriate rotation ratio. But the problem is, due to the layout of the LAMBCHOP and the complicated system of beams and pipes supporting it, the pegs that will support the gears are fixed in place.

The LAMBCHOP's engineers have given you lists identifying the placement of groups of pegs along various support beams. You need to place a gear on each peg (otherwise the gears will collide with unoccupied pegs). The engineers have plenty of gears in all different sizes stocked up, so you can choose gears of any size, from a radius of 1 on up. Your goal is to build a system where the last gear rotates at twice the rate (in revolutions per minute, or rpm) of the first gear, no matter the direction. Each gear (except the last) touches and turns the gear on the next peg to the right.

Given a list of distinct positive integers named pegs representing the location of each peg along the support beam, write a function solution(pegs) which, if there is a solution, returns a list of two positive integers a and b representing the numerator and denominator of the first gear's radius in its simplest form in order to achieve the goal above, such that radius = a/b. The ratio a/b should be greater than or equal to 1. Not all support configurations will necessarily be capable of creating the proper rotation ratio, so if the task is impossible, the function solution(pegs) should return the list [-1, -1].

For example, if the pegs are placed at [4, 30, 50], then the first gear could have a radius of 12, the second gear could have a radius of 14, and the last one a radius of 6. Thus, the last gear would rotate twice as fast as the first one. In this case, pegs would be [4, 30, 50] and solution(pegs) should return [12, 1].

The list pegs will be given sorted in ascending order and will contain at least 2 and no more than 20 distinct positive integers, all between 1 and 10000 inclusive.

Languages
=========

To provide a Java solution, edit Solution.java
To provide a Python solution, edit solution.py

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Java cases --
Input:
Solution.solution({4, 17, 50})
Output:
    -1,-1

Input:
Solution.solution({4, 30, 50})
Output:
    12,1

-- Python cases --
Input:
solution.solution([4, 30, 50])
Output:
    12,1

Input:
solution.solution([4, 17, 50])
Output:
    -1,-1
 */
public class Task22 {

    /*
    from fractions import Fraction
def answer(pegs):
    arrLength = len(pegs)
    if ((not pegs) or arrLength == 1):
        return [-1,-1]

    even = True if (arrLength % 2 == 0) else False
    sum = (- pegs[0] + pegs[arrLength - 1]) if even else (- pegs[0] - pegs[arrLength -1])

    if (arrLength > 2):
        for index in xrange(1, arrLength-1):
            sum += 2 * (-1)**(index+1) * pegs[index]

    FirstGearRadius = Fraction(2 * (float(sum)/3 if even else sum)).limit_denominator()

    # now that we have the radius of the first gear, we should again check the input array of pegs to verify that
    # the pegs radius' is atleast 1.
    # since for valid results, LastGearRadius >= 1 and FirstGearRadius = 2 * LastGearRadius
    # thus for valid results FirstGearRadius >= 2

    if FirstGearRadius < 2:
        return [-1,-1]

    currentRadius = FirstGearRadius
    for index in xrange(0, arrLength-2):
        CenterDistance = pegs[index+1] - pegs[index]
        NextRadius = CenterDistance - currentRadius
        if (currentRadius < 1 or NextRadius < 1):
            return [-1,-1]
        else:
            currentRadius = NextRadius

    return [FirstGearRadius.numerator, FirstGearRadius.denominator]
     */

    public static int[] GetFraction(double input)
    {
        double p0 = 0;
        double q0 = 1;
        double p1 = 1;
        double q1 = 0;
        double p2;
        double q2;

        double r = input - p1;
        double next_cf;
        double n =(int)Math.floor(input);
        double d = 1;
        while(true)
        {
            double a =  (double) n/d;
            q2 =  (a * q1 + q0);

            // Limit the numerator and denominator to be 256 or less
            if( q2 > 1000000)
                break;

            // remember the last two fractions
            double t0 = p0;
            p0 = p1;
            q0 = p1;
            p1 = (a*t0 + t0);
            q1=q2;
            double t = n;
            n=d;
            d = (t-a*d);

        }

        input = (double) p1 / q1;
        // hard upper and lower bounds for ratio
        if(input > 256.0)
        {
            p1 = 256;
            q1 = 1;
        }
        else if(input < 1.0 / 256.0)
        {
            p1 = 1;
            q1 = 256;
        }
        return new int[] {(int) p1,(int) q1};
    }

    public static int[] solution(int[] pegs) {
        if (pegs.length == 1) {
            return new int[]{-1, -1};
        }

        boolean even = pegs.length % 2 == 0;
        int sum = even ? -pegs[0] + pegs[pegs.length - 1] : -pegs[0] - pegs[pegs.length - 1];
        if (pegs.length > 2) {
            for (int i = 1; i < pegs.length - 1; i++) {
                sum += 2 * Math.pow(-1, i + 1) * pegs[i];
            }
        }

        double fr = 2 * (even ? (double) sum / 3 : sum);

        if (fr < 2) {
            return new int[]{-1, -1};
        }

        double cr = fr;
        for (int i = 0; i < pegs.length - 2; i++) {
            double center = pegs[i + 1] - pegs[i];
            double next = center - cr;
            if (cr < 1 || next < 1) {
                return new int[]{-1, -1};
            } else {
                cr = next;
            }

        }

        if ((fr == Math.floor(fr)) && !Double.isInfinite(fr)) {
            return new int[]{(int) fr, 1};
        }

        int[] r = GetFraction(fr);
        return new int[]{r[1],r[0]};
    }


    public static void main(String[] args) {
        final int[] ints = solution(new int[]{4, 30, 50});
        //   for (int i = 0; i < ints.length; i++) {
        //       System.out.print(ints[i]);
        //   }

        final int[] w = GetFraction((double) 12/1);
        System.out.print(w[0]);
        System.out.print(" ");
        System.out.println(w[1]);
    }
}
