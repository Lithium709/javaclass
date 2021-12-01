package com.company.cracking.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllDepths {


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

    private static class QueueElement {

        Node node;

        int level;

        public QueueElement(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    private static List<LinkedList<Node>> count(Node root) {

        List<LinkedList<Node>> result = new ArrayList<>();

        Queue<QueueElement> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.add(new QueueElement(root, 0));

        while (!queue.isEmpty()) {

            QueueElement el = queue.remove();
            if (result.size() < el.level + 1) {
                LinkedList<Node> l = new LinkedList<>();
                result.add(l);
            }
            result.get(el.level).add(el.node);
            if (el.node.left != null) {
                queue.add(new QueueElement(el.node.left, el.level + 1));
            }
            if (el.node.right != null) {
                queue.add(new QueueElement(el.node.right, el.level + 1));
            }
        }
        return result;
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
                minHeight(Arrays.copyOfRange(a, length / 2+1, length)));
    }

    public static void main(String[] args) {

        int[] input = new int[]{1, 3, 6, 8, 9, 12, 45};

        Node root = minHeight(input);

        final List<LinkedList<Node>> linkedLists = count(root);

        for (LinkedList<Node> list : linkedLists) {

            System.out.println(list.size());

        }

    }

}
