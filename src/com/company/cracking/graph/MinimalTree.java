package com.company.cracking.graph;

import java.util.Arrays;

public class MinimalTree {

    private static class Node {

        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static Node minHeight(int[] a) {

        final int length = a.length;
        if (length == 0) {
            return null;
        }

        if (length == 1) {
            return new Node(a[0], null, null);
        }

        return new Node(a[length / 2],
                minHeight(Arrays.copyOfRange(a, 0, length / 2)),
                minHeight(Arrays.copyOfRange(a, length / 2 + 1, length)));
    }

    static boolean search(Node root, int n) {

        if (root == null) {
            return false;
        }

        if (root.value == n) {
            return true;
        } else if (n < root.value) {
            return search(root.left, n);
        } else if (n > root.value) {
            return search(root.right, n);
        }
        return false;
    }

    private static int getHeight(Node root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));

    }

    static boolean isBalanced(Node root) {

        if (root == null) {
            return true;
        }

        return Math.abs(getHeight(root.right) - getHeight(root.left)) < 2 && isBalanced(root.left) && isBalanced(
                root.right);
    }

    private static boolean isCorrectLeft(Node root) {
        return root.left == null || root.left.value < root.value;
    }

    private static boolean isCorrectRight(Node root) {
        return root.right == null || root.right.value > root.value;
    }

    static boolean validateBST(Node root) {

        if (root == null) {
            return true;
        }

        return isCorrectRight(root) && isCorrectLeft(root)
                && validateBST(root.right) && validateBST(root.left);

    }

    public static void main(String[] args) {

        int[] input = new int[]{1, 3, 6, 8, 9, 12, 45};

        Node root = minHeight(input);
/*
        System.out.println(root.value);

        System.out.println(search(root, 2));
        System.out.println(search(root, 7));
        System.out.println(search(root, 9));

        System.out.println(getHeight(root));

        System.out.println(isBalanced(root));
*/
        System.out.println(validateBST(root));
    }

}
