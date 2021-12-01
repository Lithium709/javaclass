package com.google.foobar;
/*
Doomsday Fuel
        =============

        Making fuel for the LAMBCHOP's reactor core is a tricky process because of the exotic matter involved. It starts as raw ore, then during processing, begins randomly changing between forms, eventually reaching a stable form. There may be multiple stable forms that a sample could ultimately reach, not all of which are useful as fuel.

        Commander Lambda has tasked you to help the scientists increase fuel creation efficiency by predicting the end state of a given ore sample. You have carefully studied the different structures that the ore can take and which transitions it undergoes. It appears that, while random, the probability of each structure transforming is fixed. That is, each time the ore is in 1 state, it has the same probabilities of entering the next state (which might be the same state).  You have recorded the observed transitions in a matrix. The others in the lab have hypothesized more exotic forms that the ore can become, but you haven't seen all of them.

        Write a function solution(m) that takes an array of array of nonnegative ints representing how many times that state has gone to the next state and return an array of ints for each terminal state giving the exact probabilities of each terminal state, represented as the numerator for each state, then the denominator for all of them at the end and in simplest form. The matrix is at most 10 by 10. It is guaranteed that no matter which state the ore is in, there is a path from that state to a terminal state. That is, the processing will always eventually end in a stable state. The ore starts in state 0. The denominator will fit within a signed 32-bit integer during the calculation, as long as the fraction is simplified regularly.

        For example, consider the matrix m:
        [
        [0,1,0,0,0,1],  # s0, the initial state, goes to s1 and s5 with equal probability
        [4,0,0,3,2,0],  # s1 can become s0, s3, or s4, but with different probabilities
        [0,0,0,0,0,0],  # s2 is terminal, and unreachable (never observed in practice)
        [0,0,0,0,0,0],  # s3 is terminal
        [0,0,0,0,0,0],  # s4 is terminal
        [0,0,0,0,0,0],  # s5 is terminal
        ]
        So, we can consider different paths to terminal states, such as:
        s0 -> s1 -> s3
        s0 -> s1 -> s0 -> s1 -> s0 -> s1 -> s4
        s0 -> s1 -> s0 -> s5
        Tracing the probabilities of each, we find that
        s2 has probability 0
        s3 has probability 3/14
        s4 has probability 1/7
        s5 has probability 9/14
        So, putting that together, and making a common denominator, gives an answer in the form of
        [s2.numerator, s3.numerator, s4.numerator, s5.numerator, denominator] which is
        [0, 3, 2, 9, 14].

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
        Solution.solution({{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0,0}, {0, 0, 0, 0, 0}})
        Output:
        [7, 6, 8, 21]

        Input:
        Solution.solution({{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}})
        Output:
        [0, 3, 2, 9, 14]
*/


import java.util.Random;

public class Task32 {


    public static int[] solution(int[][] m) {

        Random rand = new Random();
        int trials = 1000000;

        final int size = m.length;
        if (size == 1) {
            return new int[]{1, 1};
        }
        int[] outcomes = new int[size];
        int[] totals = new int[size];
        int max_total = -1;
        int end_state_total = 0;

        int[] totals_col = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totals[i] += m[i][j];
                totals_col[i] += m[j][i];
            }
            if (totals[i] == 0) {
                end_state_total++;
            }
            max_total = Math.max(max_total, totals[i]);
        }

        for (int t = 0; t < trials; t++) {

            int s = 0;
            while (true) {

                int outs_sum = totals[s];

                // check if final state
                if (outs_sum == 0) {
                    outcomes[s]++;
                    break;
                }

                // change state randomly
                int r = rand.nextInt(outs_sum) + 1;
                int sum = 0;
                for (int i = 0; i < size; i++) {
                    sum += m[s][i];
                    if (r <= sum) {
                        s = i;
                        break;
                    }
                }

            }
        }

       for (int i = 0; i < size; i++) {
            System.out.print(outcomes[i]);
            System.out.print(" ");
        }


          System.out.println();
        // present as fractions
        int[] denoms = new int[size];
        int[] numers = new int[size];
        for (int i = 1; i < size; i++) {
            if (outcomes[i] == 0) {
                numers[i] = 0;
                denoms[i] = 1;
                continue;
            }
            double prob = (double) outcomes[i] / trials;
            int[] frac = farey(prob, 3 * max_total);
            numers[i] = frac[0];
            denoms[i] = frac[1];
        }

        // find max denominator
        int max_denom = 0;
        for (int i = 0; i < size; i++) {
            max_denom = Math.max(max_denom, denoms[i]);
        }

        int[] results = new int[end_state_total + 1];

        for (int i = 1, j = 0; i < size; i++) {
            if (numers[i] == 0 && totals_col[i] != 0) {
                continue;
            }
            results[j++] = numers[i] * (max_denom / denoms[i]);

        }
        results[end_state_total] = max_denom;

        return results;
    }

    /*

    0.213803
    0.142834
    0.643363
    //  0 0  3  1  9
    //  1 1  14 7  14

    //  0  1  2  8
    //  1  3  7  21
    */

    public static int[] farey(double x, int N) {

        int a = 0;
        int b = 1;
        int c = 1;
        int d = 1;

        double e = 0.01d;

        while (b <= N && d <= N) {
            double median = (double) (a + c) / (b + d);
            if (Math.abs(x - median) < e) {
                if (b + d <= N) {
                    return new int[]{a + c, b + d};
                } else if (d > b) {
                    return new int[]{c, d};
                } else {
                    return new int[]{a, b};
                }
            } else if (x > median) {
                a += c;
                b += d;
            } else {
                c += a;
                d += b;
            }
        }

        if (b > N) {
            return new int[]{c, d};
        } else {
            return new int[]{a, b};
        }

    }


    public static int gcd(int denumerator, int numerator) {
        if (denumerator == 0) {
            return numerator;
        }
        return gcd(numerator % denumerator, denumerator);
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
/*
        int res1[] = solution(new int[][]{
                {0, 1, 0, 0, 0, 1},
                {4, 0, 0, 3, 2, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}}

        );
        for (int i = 0; i < res1.length; i++) {
            System.out.print(res1[i]);
            System.out.print(",");
        }
        System.out.println();

        int res2[] = solution(new int[][]{
                {0, 2, 1, 0, 0},
                {0, 0, 0, 3, 4},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}}

        );

        for (int i = 0; i < res2.length; i++) {
            System.out.print(res2[i]);
            System.out.print(",");
        }
        System.out.println();

        int res3[] = solution(new int[][]{
                {1, 2, 3, 0, 0, 0},
                {4, 5, 6, 0, 0, 0},
                {7, 8, 9, 1, 0, 0},
                {0, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}}

        );

        for (int i = 0; i < res3.length; i++) {
            System.out.print(res3[i]);
            System.out.print(",");
        }
        System.out.println();

        int res4[] = solution(new int[][]{
                {0}}
        );
        for (int i = 0; i < res4.length; i++) {
            System.out.print(res4[i]);
            System.out.print(",");
        }
        System.out.println();
*/
        int res5[] = solution(new int[][]{
                        {0, 0, 12, 0, 15, 0, 0, 0, 1, 8},
                        {0, 0, 60, 0, 0, 7, 13, 0, 0, 0},
                        {0, 15, 0, 8, 7, 0, 0, 1, 9, 0},
                        {23, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        {37, 35, 0, 0, 0, 0, 3, 21, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        for (int i = 0; i < res5.length; i++) {
            System.out.print(res5[i]);
            System.out.print(",");
        }
        System.out.println();
/*
        int[] res0 = farey(0.213803d, 100);
        //System.out.print(res0[0]);
        //System.out.println(res0[1]);
        int[] res1 = farey(0.142834d, 100);
        //System.out.print(res1[0]);
        //System.out.println(res1[1]);
        int[] res2 = farey(0.643363d, 100);
        //System.out.print(res2[0]);
        //System.out.println(res2[1]);

        int lcd = lcm(res0[1], res1[0]);

        int num1 = res0[0] * (lcd / res0[1]);
        int num2 = res1[0] * (lcd / res1[1]);
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(lcd);

 */
    }

}
