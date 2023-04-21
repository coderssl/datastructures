package com.shstu.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(array);
        tree.preOrder();
        System.out.println();
        tree.infixOrder();
        System.out.println();
        tree.postOrder();
    }
}

class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void postOrder() {
        this.postOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void preOrder() {
        this.preOrder(0);
    }


    //后序遍历
    public void postOrder(int index) {
        if (array == null || array.length == 0 || index < 0 || index >= array.length) {
            return;
        }
        if ((2 * index + 1) < array.length) {
            postOrder(2 * index + 1);
        }
        if ((2 * index + 2) < array.length) {
            postOrder(2 * index + 2);
        }
        System.out.print(array[index] + "\t");
    }

    //中序遍历
    public void infixOrder(int index) {
        if (array == null || array.length == 0 || index < 0 || index >= array.length) {
            return;
        }
        if ((2 * index + 1) < array.length) {
            infixOrder(2 * index + 1);
        }
        System.out.print(array[index] + "\t");
        if ((2 * index + 2) < array.length) {
            infixOrder(2 * index + 2);
        }
    }

    //前序遍历
    public void preOrder(int index) {
        if (array == null || array.length == 0 || index < 0 || index >= array.length) {
            return;
        }
        System.out.print(array[index] + "\t");
        if ((2 * index + 1) < array.length) {
            preOrder(2 * index + 1);
        }
        if ((2 * index + 2) < array.length) {
            preOrder(2 * index + 2);
        }
    }
}
