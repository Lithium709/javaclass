package com.google.foobar;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.swap;
import java.util.List;

/*
Running with Bunnies
====================

You and your rescued bunny prisoners need to get out of this collapsing death trap of a space station - and fast! Unfortunately, some of the bunnies have been weakened by their long imprisonment and can't run very fast. Their friends are trying to help them, but this escape would go a lot faster if you also pitched in. The defensive bulkhead doors have begun to close, and if you don't make it through in time, you'll be trapped! You need to grab as many bunnies as you can and get through the bulkheads before they close.

The time it takes to move from your starting point to all of the bunnies and to the bulkhead will be given to you in a square matrix of integers. Each row will tell you the time it takes to get to the start, first bunny, second bunny, ..., last bunny, and the bulkhead in that order. The order of the rows follows the same pattern (start, each bunny, bulkhead). The bunnies can jump into your arms, so picking them up is instantaneous, and arriving at the bulkhead at the same time as it seals still allows for a successful, if dramatic, escape. (Don't worry, any bunnies you don't pick up will be able to escape with you since they no longer have to carry the ones you did pick up.) You can revisit different spots if you wish, and moving to the bulkhead doesn't mean you have to immediately leave - you can move to and from the bulkhead to pick up additional bunnies if time permits.

In addition to spending time traveling between bunnies, some paths interact with the space station's security checkpoints and add time back to the clock. Adding time to the clock will delay the closing of the bulkhead doors, and if the time goes back up to 0 or a positive number after the doors have already closed, it triggers the bulkhead to reopen. Therefore, it might be possible to walk in a circle and keep gaining time: that is, each time a path is traversed, the same amount of time is used or added.

Write a function of the form solution(times, time_limit) to calculate the most bunnies you can pick up and which bunnies they are, while still escaping through the bulkhead before the doors close for good. If there are multiple sets of bunnies of the same size, return the set of bunnies with the lowest prisoner IDs (as indexes) in sorted order. The bunnies are represented as a sorted list by prisoner ID, with the first bunny being 0. There are at most 5 bunnies, and time_limit is a non-negative integer that is at most 999.

For instance, in the case of
[
  [0, 2, 2, 2, -1],  # 0 = Start
  [9, 0, 2, 2, -1],  # 1 = Bunny 0
  [9, 3, 0, 2, -1],  # 2 = Bunny 1
  [9, 3, 2, 0, -1],  # 3 = Bunny 2
  [9, 3, 2, 2,  0],  # 4 = Bulkhead
]
and a time limit of 1, the five inner array rows designate the starting point, bunny 0, bunny 1, bunny 2, and the bulkhead door exit respectively. You could take the path:

Start End Delta Time Status
    -   0     -    1 Bulkhead initially open
    0   4    -1    2
    4   2     2    0
    2   4    -1    1
    4   3     2   -1 Bulkhead closes
    3   4    -1    0 Bulkhead reopens; you and the bunnies exit

With this solution, you would pick up bunnies 1 and 2. This is the best combination for this space station hallway, so the answer is [1, 2].

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
Solution.solution({{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}}, 3)
Output:
    [0, 1]

Input:
Solution.solution({{0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0}}, 1)
Output:
    [1, 2]

-- Python cases --
Input:
solution.solution([[0, 2, 2, 2, -1], [9, 0, 2, 2, -1], [9, 3, 0, 2, -1], [9, 3, 2, 0, -1], [9, 3, 2, 2, 0]], 1)
Output:
    [1, 2]

Input:
solution.solution([[0, 1, 1, 1, 1], [1, 0, 1, 1, 1], [1, 1, 0, 1, 1], [1, 1, 1, 0, 1], [1, 1, 1, 1, 0]], 3)
Output:
    [0, 1]

 */
public class Task42 {


    public static int[][] fullbellmanford(int graph[][], int V, int E) {

        int[][] res = new int[V][V];

        for (int i = 0; i < V; i++) {
            final int[] bellmanford = bellmanford(graph, V, E, i);
            if(bellmanford == null){
                return null;
            }
            res[i] = bellmanford;
        }

        return res;
    }


    private static Integer[] findShortest(int times[][], int V, int times_limit) {

        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i < V - 1; i++) {
            arr.add(i);
        }

        for (int k = V - 2; k > 0; k--) {
            final List<Integer[]> permutations = permutation(arr,k);

            for (int j =0; j < permutations.size()-1 ; j++) {

                Integer[] bunnies = permutations.get(j);

                int[] path = new int[bunnies.length + 2];
                path[0] = 0;
                for (int i = 0; i < bunnies.length; i++) {
                    path[i + 1] = bunnies[i];
                }
                path[bunnies.length + 1] = V - 1;
                int cost = 0;
                for (int i = 0; i < bunnies.length + 1; i++) {
                    cost += times[path[i]][path[i + 1]];
                }
                if (cost <= times_limit) {
                    Arrays.sort(bunnies);
                    return bunnies;
                }
            }
        }
        return null;
    }

    public static List<Integer[]> permutation(List<Integer> arr, int k) {
        List<Integer[]> res = new ArrayList<>();
        permute(arr, arr.size(), k, res);
        res.sort((a1,a2)->{
            int s1=0;
            for (int i = 0; i < a1.length; i++) {
                s1 += a1[i]*Math.pow(10, a2.length-1-i);
            }
            int s2=0;
            for (int i = 0; i < a2.length; i++) {
                s2 += a2[i]*Math.pow(10, a2.length-1-i);
            }
            return s1-s2;
        });
        return res;
    }

    private static void permute(List<Integer> arr, int n, int k, List<Integer[]> permutations) {

        if (k == 0) {
            Integer[] singlePermutation = new Integer[ arr.size()-n];
            for (int i = n; i < arr.size(); i++) {
                singlePermutation[i-n] = arr.get(i);
            }
            permutations.add(singlePermutation);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(arr, i, n - 1);
            permute(arr, n - 1, k - 1, permutations);
            swap(arr, i, n - 1);
        }

    }

    public static int[] bellmanford(int graph[][], int V, int E, int start) {

        // Initialize distance of all vertices as infinite.
        int[] dis = new int[V];
        for (int i = 0; i < V; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        // initialize distance of source as 0
        dis[start] = 0;

        // Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other
        // vertex can have at-most |V| - 1 edges
        for (int i = 0; i < V - 1; i++) {

            for (int j = 0; j < E; j++) {
                if (graph[j][2] != Integer.MAX_VALUE && dis[graph[j][0]] != Integer.MAX_VALUE) {
                    if (dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]]) {
                        dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2];
                    }
                }
            }
        }

        // check for negative-weight cycles.
        // The above step guarantees shortest
        // distances if graph doesn't contain
        // negative weight cycle. If we get a
        // shorter path, then there is a cycle.

        for (int i = 0; i < E; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            int weight = graph[i][2];
            if (dis[x] != Integer.MAX_VALUE &&
                    dis[x] + weight < dis[y]) {
                return null;
            }
        }

        return dis;
    }


    public static final int[] solution(int times[][], int times_limit) {

        final int size = times.length;
        int E = size * size - size;
        int[][] graph = new int[E][3];
        int index = 0;
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }
                graph[index++] = new int[]{i, j, times[i][j]};
            }

        }

        final int[][] fullbellmanford = fullbellmanford(graph, size, E);
        if (fullbellmanford == null){
            int[] res = new int[size-2];
            for (int i = 0; i < res.length; i++) {
                res[i]=i;
            }
            return res;
        }

        final Integer[] shortest = findShortest(fullbellmanford, size, times_limit);

        int[] res = new int[shortest.length];
        for (int i = 0; i < shortest.length; i++) {
            res[i] = shortest[i]-1;
        }

        return res;

    }

    public static void main(String[] args) {

        final int[] solution1 = solution(new int[][]{
                {0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0}}, 3);//[0, 1]

       assert Arrays.equals(solution1, new int[]{0, 1});

        final int[] solution2 = solution(new int[][]{
                {0, 2, 2, 2, -1},
                {9, 0, 2, 2, -1},
                {9, 3, 0, 2, -1},
                {9, 3, 2, 0, -1},
                {9, 3, 2, 2, 0}}, 1);//[1, 2]
        assert Arrays.equals(solution2, new int[]{1, 2});


/*
         final List<Integer[]> permutation = permutation(Arrays.asList(1, 2, 3),3);
      //  final List<Integer[]> permutation = generate(3, 3);
        for (int i = 0; i < permutation.size(); i++) {
            final Integer[] integers = permutation.get(i);
            for (int j = 0; j < integers.length; j++) {
                System.out.print(integers[j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        */
    }


    public static void out(int[] a) {
        for (int i : a) {
            System.out.println(i);
        }

    }

}
