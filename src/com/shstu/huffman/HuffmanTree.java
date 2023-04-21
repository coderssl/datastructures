package com.shstu.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    private Node root;
    public static void main(String[] args) {
        int[] array = {3, 6, 2, 1, 7, 5, 8};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        HuffmanTree huffmanTree = new HuffmanTree(array);
        huffmanTree.preOrder();
    }

    public HuffmanTree(int[] array) {
        this.root = huffmanTree(array);
    }

    public void preOrder() {
        if (root == null) {
            return;
        }
        root.preOrder();
    }

    public Node huffmanTree(int[] array) {
        List<Node> nodes = nodeList(array);
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(leftNode.value + rightNode.value);

            parentNode.left = leftNode;
            parentNode.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    public List<Node> nodeList(int[] array) {
        List<Node> nodes = new ArrayList<>();
        for (int i : array) {
            nodes.add(new Node(i));
        }
        return nodes;
    }
}

class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}