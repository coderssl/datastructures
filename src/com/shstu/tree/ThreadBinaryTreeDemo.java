package com.shstu.tree;

import java.util.Stack;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        NatureNodes root = new NatureNodes(1, "jack");
        NatureNodes node2 = new NatureNodes(2, "bob");
        NatureNodes node3 = new NatureNodes(3, "scott");
        NatureNodes node4 = new NatureNodes(4, "smith");
        NatureNodes node5 = new NatureNodes(5, "milan");
        NatureNodes node6 = new NatureNodes(6, "mary");
        NatureNodes node7 = new NatureNodes(7, "tom");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        //node3.setLeft(node6);
        //node3.setRight(node7);

        ThreadBinaryTree tree = new ThreadBinaryTree(root);
//        tree.postThreadNodes(root);
//        System.out.println(node5.getLeft());
//        System.out.println(node5.getRight());

        tree.postThreadNodes(root);
        tree.postOrderThreadNodes(root);
    }
}

class ThreadBinaryTree {
    private NatureNodes pre;
    private NatureNodes root;

    public ThreadBinaryTree(NatureNodes root) {
        this.root = root;
    }

    //后序遍历前序线索化二叉树
    //非递归
    public void postThreadOrder(NatureNodes node) {
        Stack<NatureNodes> stack = new Stack<>();
        while (node != null) {
            while (node.getRightType() == 0) {
                stack.push(node);
                node = node.getRight();
            }
            stack.push(node);
            node = node.getLeft();
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    //后序遍历前序线索化二叉树
    //递归
    public void postOrderThreadNodes(NatureNodes node) {
        if (node == null) {
            return;
        }
        if (node.getLeftType() == 0) {
            postOrderThreadNodes(node.getLeft());
        }

        if (node.getRightType() == 0) {
            postOrderThreadNodes(node.getRight());
        }

        System.out.println(node);
    }

    //中序遍历前序线索化二叉树
    //非递归
    public void infixOrderThreadNodes(NatureNodes node) {
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //前序遍历前序线索化二叉树
    //非递归
    public void preOrderThreadNodes(NatureNodes node) {
        while (node != null) {
            while (node.getLeftType() == 0) {
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            node = node.getRight();
        }
    }

    //前序线索化二叉树
    public void preThreadNodes(NatureNodes node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if (node.getLeftType() == 0) {
            preThreadNodes(node.getLeft());
        }
        if (node.getRightType() == 0) {
            preThreadNodes(node.getRight());
        }
    }

    //中序线索化二叉树
    public void infixThreadNodes(NatureNodes node) {
        if (node == null) {
            return;
        }
        infixThreadNodes(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        infixThreadNodes(node.getRight());
    }

    //后序线索化二叉树
    public void postThreadNodes(NatureNodes node) {
        if (node == null) {
            return;
        }
        //if (node.getLeftType() == 0) {
        postThreadNodes(node.getLeft());
        //}
        //if (node.getRightType() == 0) {
        postThreadNodes(node.getRight());
        //}
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }
}

class NatureNodes {
    private int id;
    private String name;
    private NatureNodes left;
    private NatureNodes right;
    private int leftType;
    private int rightType;

    public NatureNodes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NatureNodes getLeft() {
        return left;
    }

    public void setLeft(NatureNodes left) {
        this.left = left;
    }

    public NatureNodes getRight() {
        return right;
    }

    public void setRight(NatureNodes right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "NatureNodes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
