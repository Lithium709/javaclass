package com.company;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {


    //  Definition for a binary tree node.
      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        // bfs
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            stack.addFirst(node);
            if(node != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        while (!stack.isEmpty() && stack.peekFirst() == null){
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!stack.isEmpty()) {
            TreeNode node = stack.removeLast();
            if(node == null) {
                sb.append("null");
            } else {
                sb.append(node.val);
            }
            if(!stack.isEmpty()){
                sb.append(",");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] arr = data.substring(1, data.length() - 1).split(",");
        int n = arr.length;
        if( n == 0) {
            return null;
        }

        // initialize nodes
        TreeNode[] nodes = new TreeNode[arr.length + 1];
        for (int i = 0; i < n; i++) {
            if(!arr[i].equals( "null")) {
                TreeNode node = new TreeNode(Integer.valueOf(arr[i]));
                nodes[i + 1] = node;
            }
        }

        n++;

        // interlinking
        // 9/2
        for (int i = 1;  i < n / 2 + 1 ; i++ ) {
            if(nodes[i] != null) {
                nodes[i].left = nodes[2 * i];
                if (2 * i + 1 < n ) {
                    nodes[i].right = nodes[2 * i + 1];
                }
            }
        }

        return nodes[1];
    }


    public static void main(String[] args) {
        TreeNode root = deserialize("[1,2,3,null,null,4,5,6,7]");

         System.out.println( serialize(root));
    }
}
