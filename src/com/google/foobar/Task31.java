package com.google.foobar;
/*
Prepare the Bunnies' Escape
===========================

You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny prisoners, but once they're free of the prison blocks, the bunnies are going to need to escape Lambda's space station via the escape pods as quickly as possible. Unfortunately, the halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies. Fortunately, Commander Lambda has put you in charge of a remodeling project that will give you the opportunity to make things a little easier for the bunnies. Unfortunately (again), you can't just remove all obstacles between the bunnies and the escape pods - at most you can remove one wall per escape pod path, both to maintain structural integrity of the station and to avoid arousing Commander Lambda's suspicions.

You have maps of parts of the space station, each starting at a prison exit and ending at the door to an escape pod. The map is represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls. The door out of the prison is at the top left (0,0) and the door into an escape pod is at the bottom right (w-1,h-1).

Write a function solution(map) that generates the length of the shortest path from the prison door to the escape pod, where you are allowed to remove one wall as part of your remodeling plans. The path length is the total number of nodes you pass through, counting both the entrance and exit nodes. The starting and ending positions are always passable (0). The map will always be solvable, though you may or may not need to remove a wall. The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.

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
solution.solution([[0, 1, 1, 0], [0, 0, 0, 1], [1, 1, 0, 0], [1, 1, 1, 0]])
Output:
    7

Input:
solution.solution([[0, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 0], [0, 0, 0, 0, 0, 0], [0, 1, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0]])
Output:
    11

 */


import java.util.LinkedList;
import java.util.Queue;

public class Task31 {

    // To store matrix cell coordinates
    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // A Data Structure for queue used in BFS
    static class QueueElement {

        Point pt; // The cordinates of a cell
        int dist; // cell's distance of from the source

        public QueueElement(Point pt, int dist) {
            this.pt = pt;
            this.dist = dist;
        }
    }


    // check whether given cell (row, col)
    // is a valid cell or not.
    static boolean isValid(int row, int col, int h, int w) {
        // return true if row number and
        // column number is in range
        return (row >= 0) && (row < h) &&
                (col >= 0) && (col < w);
    }

    // These arrays are used to get row and column
// numbers of 4 neighbours of a given cell
    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    public static int solution(int[][] mat) {

        int minDist = sol(mat);
        final int h = mat.length;
        final int w = mat[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = 0;
                    minDist = Math.min(minDist, sol(mat));
                    mat[i][j] = 1;
                }
            }
        }
        return minDist;
    }


    // function to find the shortest path between
// a given source cell to a destination cell.
    public static int sol(int[][] mat) {
        // check source and destination cell
        // of the matrix have value 1
        final int h = mat.length;
        final int w = mat[0].length;
        boolean[][] visited = new boolean[h][w];

        // Mark the source cell as visited
        visited[0][0] = true;

        // Create a queue for BFS
        Queue<QueueElement> q = new LinkedList<>();

        // Distance of source cell is 1
        QueueElement s = new QueueElement(new Point(0, 0), 1);
        q.add(s); // Enqueue source cell

        // Do a BFS starting from source cell
        while (!q.isEmpty()) {
            QueueElement curr = q.peek();
            Point pt = curr.pt;
            // If we have reached the destination cell,
            // we are done
            if (pt.x == h - 1 && pt.y == w - 1) {
                return curr.dist;
            }
            // Otherwise dequeue the front cell
            // in the queue and enqueue
            // its adjacent cells
            q.remove();

            for (int i = 0; i < 4; i++) {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                // if adjacent cell is valid, has path
                // and not visited yet, enqueue it.
                if (isValid(row, col, h, w) && !visited[row][col] && mat[row][col] == 0) {
                    // mark cell as visited and enqueue it
                    visited[row][col] = true;
                    QueueElement adjacentCell = new QueueElement(new Point(row, col),
                            curr.dist + 1);
                    q.add(adjacentCell);
                }
            }
        }

        // Return -1 if destination cannot be reached
        return 999999;
    }

    public static void main(String[] args) {

        System.out.println(solution(new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 0}}));
        System.out.println(solution(
                new int[][]{
                        {0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0}}));

        System.out.println(solution(
                new int[][]{
                        {0, 1, 1},
                        {0, 0, 1},
                        {1, 1, 0},
                }));
    }

}
