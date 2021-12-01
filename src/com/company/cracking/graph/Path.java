package com.company.cracking.graph;

public class Path {

    private static boolean hasPath(int[][] graph, int a, int b) {

        if (graph[a][b] > 0) {
            return true;
        }
        for (int i = 0; i < graph[a].length; i++) {
            if (i != b && graph[a][i] > 0) {
                return hasPath(graph, i, b);
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int graph[][] = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };

        System.out.println(hasPath(graph, 4, 1));
        System.out.println(hasPath(graph, 0, 4));
    }

}
