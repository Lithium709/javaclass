package com.google.foobar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
Bringing a Gun to a Guard Fight
===============================

Uh-oh - you've been cornered by one of Commander Lambdas elite guards! Fortunately, you grabbed a beam weapon from an abandoned guard post while you were running through the station, so you have a chance to fight your way out. But the beam weapon is potentially dangerous to you as well as to the elite guard: its beams reflect off walls, meaning you'll have to be very careful where you shoot to avoid bouncing a shot toward yourself!

Luckily, the beams can only travel a certain maximum distance before becoming too weak to cause damage. You also know that if a beam hits a corner, it will bounce back in exactly the same direction. And of course, if the beam hits either you or the guard, it will stop immediately (albeit painfully).

Write a function solution(dimensions, your_position, guard_position, distance) that gives an array of 2 integers of the width and height of the room, an array of 2 integers of your x and y coordinates in the room, an array of 2 integers of the guard's x and y coordinates in the room, and returns an integer of the number of distinct directions that you can fire to hit the elite guard, given the maximum distance that the beam can travel.

The room has integer dimensions [1 < x_dim <= 1250, 1 < y_dim <= 1250]. You and the elite guard are both positioned on the integer lattice at different distinct positions (x, y) inside the room such that [0 < x < x_dim, 0 < y < y_dim]. Finally, the maximum distance that the beam can travel before becoming harmless will be given as an integer 1 < distance <= 10000.

For example, if you and the elite guard were positioned in a room with dimensions [3, 2], your_position [1, 1], guard_position [2, 1], and a maximum shot distance of 4, you could shoot in seven different directions to hit the elite guard (given as vector bearings from your location): [1, 0], [1, 2], [1, -2], [3, 2], [3, -2], [-3, 2], and [-3, -2]. As specific examples, the shot at bearing [1, 0] is the straight line horizontal shot of distance 1, the shot at bearing [-3, -2] bounces off the left wall and then the bottom wall before hitting the elite guard with a total shot distance of sqrt(13), and the shot at bearing [1, 2] bounces off just the top wall before hitting the elite guard with a total shot distance of sqrt(5).

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
Solution.solution([3,2], [1,1], [2,1], 4)
Output:
    7

Input:
Solution.solution([300,275], [150,150], [185,100], 500)
Output:
    9



 */


public class Task4c {


    public static int mirror(int x, int i, int w) {
        if (i == 0) {
            return x;
        }
        if (i > 0) {
            return (i % 2 == 1 ? i + 1 : i) * w + ((int) Math.pow(-1, i)) * x;
        }
        return (-i % 2 == 1 ? i + 1 : i) * w + ((int) Math.pow(-1, i)) * x;
    }

    private static class Plane {

        int x, y;

        public Plane(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] rowNum = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] colNum = {0, -1, -1, -1, 0, 1, 1, 1};

    private static boolean isValid(int row, int col, int n, int m) {

        return ((row >= 0) && (row < n) && (col >= 0) && (col < m)) ||
                ((row < 0) && (-row <= n) && (col >= 0) && (col < m)) ||
                ((row < 0) && (-row <= n) && (col < 0) && (-col <= m)) ||
                ((row >= 0) && (row < n) && (col < 0) && (-col <= m))
                ;

    }

    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {

        int width = dimensions[0];
        int height = dimensions[1];

        final int v1 = your_position[0] - guard_position[0];
        final int v2 = your_position[1] - guard_position[1];
        int dist_to_guard = v1 * v1 + v2 * v2;
        if (distance * distance < dist_to_guard) {
            return 0;
        }
        if (distance * distance < dist_to_guard) {
            return 1;
        }

        int n = distance / width + 1;
        int m = distance / height + 1;

        Map<Double, Long> angles = new HashMap<>();
        Queue<Plane> queue = new LinkedList<>();
        int count = 0;

        int unreachable = 0;
        int blocked = 0;
        int angle = 0;

        boolean[][] visited = new boolean[2 * n][2 * m];

        queue.add(new Plane(0, 0));

        while (!queue.isEmpty()) {

            Plane p = queue.remove();

            int x = mirror(your_position[0], p.x, width);
            int y = mirror(your_position[1], p.y, height);

            int g = mirror(guard_position[0], p.x, width);
            int h = mirror(guard_position[1], p.y, height);
            final long d1 = (long) g - your_position[0];
            final long d2 = (long) h - your_position[1];
            final long c1 = (long) x - your_position[0];
            final long c2 = (long) y - your_position[1];
            final long c = c1 * c1 + c2 * c2;
            long d = d1 * d1 + d2 * d2;
            final double a = key(c1, c2);
            final double key = key(d1, d2);
            if(c < distance * distance && c > 0){

                if(angles.get(a)!=null){
                    if(angles.get(a)>=c){
                        angles.put(a,c);
                    }
                }else {
                    angles.put(a,c);
                }

            }
            if(d < distance * distance && d > 0){
                if(angles.get(key)!=null){
                    if(angles.get(key)>=d){
                        count++;
                        angles.put(key,d);
                    }
                }else {
                    count++;
                    angles.put(key,c);
                }
            }
            for (int i = 0; i < 8; i++) {
                int row = p.x + rowNum[i];
                int col = p.y + colNum[i];
                if (isValid(row, col, n, m) && !visited[n + row][m + col]) {
                    visited[n + row][m + col] = true;
                    queue.add(new Plane(row, col));
                }
            }

        }
        return count;

    }


    private static double key(double c1, double c2) {
        return Math.atan2(c1, c2);
    }

    public static void main(String[] args) {

          System.out.println(solution(new int[]{3, 2}, new int[]{1, 1}, new int[]{2, 1}, 4)); // expected 7
        //   System.out.println(solution(new int[]{3, 2}, new int[]{1, 1}, new int[]{2, 1}, 10000)); // expected 7
        System.out.println(solution(new int[]{10, 10}, new int[]{4, 4}, new int[]{3, 3}, 5000)); // 739323
        //  System.out.println(solution(new int[]{10, 10}, new int[]{4, 4}, new int[]{3, 3}, 50)); // 73932
        //  System.out.println(solution(new int[]{42, 59}, new int[]{34, 44}, new int[]{6, 34}, 5000)); // ??
          System.out.println(solution(new int[]{23, 10}, new int[]{6, 4}, new int[]{3, 2}, 23)); //8
        //   System.out.println(solution(new int[]{300, 275}, new int[]{150, 150}, new int[]{185, 100}, 500)); // 9
           System.out.println(solution(new int[]{2, 5}, new int[]{1, 2}, new int[]{1, 4}, 11)); // 27
        //   System.out.println(solution(new int[]{1250, 1250}, new int[]{300, 400}, new int[]{400, 400}, 10000));
        //  System.out.println(mirror(1, 1, 2));
      /*  System.out.println(mirror(1, 1, 2));
        System.out.println(mirror(1, 2, 2));
        System.out.println(mirror(1, 3, 2));
        System.out.println(mirror(1, 4, 2));
        System.out.println(mirror(1, 5, 2)); */

    }

}
