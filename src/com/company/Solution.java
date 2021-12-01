package com.company;

import java.util.*;

class Node{

    private static List<Node> nodes = new ArrayList<>();
    static Set<Node> visited = new HashSet<>();

    public static final int edgeLength = 6;

    private int id;
    Set<Node> adjacent = new HashSet<>();
    public Node(int id){
        this.id = id;
    }
    public void addAdjacent(Node node){
        this.adjacent.add(node);
        node.adjacent.add(this);
    }

    static int findShortestPath(Node v, Node u){
        visited.clear();
        int res = findPath(v, u);
        return res<0?-1:res;
    }

    private static int findPath(Node v, Node u){
        if(v.adjacent.contains(u)) return edgeLength;
        for(Node n: v.adjacent){
            if(!visited.contains(n)){
                visited.add(n);
                return edgeLength + findPath(n, u);
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", adjacent=" + adjacent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {

        return id%3;
    }

    static void addEdge(int i, int j){

        Node n1 = new Node(i);
        Node n2 = new Node(j);
        if(!nodes.contains(n1)){
            nodes.add(n1);
            n1.addAdjacent(n2);
        }
        if(!nodes.contains(n2)){
            nodes.add(n2);
        }
    }
}

public class Solution {



    public static void main(String[] args) {


        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n4.addAdjacent(root);
        n4.addAdjacent(n3);
        root.addAdjacent(n2);
        root.addAdjacent(n3);
        Node isolated = new Node(4);

        System.out.println(Node.findShortestPath(n2, n4));
    /*
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        int[][][] queries = new int[q][][];
        for(int i=0;i<q;i++){

            int n = scanner.nextInt(); // nodes
            int m = scanner.nextInt(); //edges
            for(int k=0;k<m;k++){
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                Node.addEdge(u,v);
            }
            int root = scanner.nextInt(); //root

        }

*/

    }
}
