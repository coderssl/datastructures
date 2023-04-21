package com.shstu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        NatureNode root = new NatureNode(1, "spring");
        NatureNode node2 = new NatureNode(2, "summer");
        NatureNode node3 = new NatureNode(3, "autumn");
        NatureNode node4 = new NatureNode(4, "winter");
        NatureNode node5 = new NatureNode(5, "wind");
        NatureNode node6 = new NatureNode(6, "snow");
        NatureNode node7 = new NatureNode(7, "rain");
        NatureNode node8 = new NatureNode(8, "sun");
        tree.setHead(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);
        node4.setLeft(node6);
        node4.setRight(node7);
        node5.setRight(node8);

        tree.preOrder();
//        System.out.println("==============");
//        tree.infixOrder();
//        System.out.println("==============");
//        tree.postOrder();

//        NatureNode result = tree.preOrderSearch(5);
//        if (result == null) {
//            System.out.println("没有找到~");
//        } else {
//            System.out.println(result);
//        }
//
//        NatureNode result2 = tree.infixOrderSearch(5);
//        if (result2 == null) {
//            System.out.println("没有找到~");
//        } else {
//            System.out.println(result2);
//        }
//
//        NatureNode result3 = tree.postOrderSearch(5);
//        if (result3 == null) {
//            System.out.println("没有找到~");
//        } else {
//            System.out.println(result3);
//        }
        tree.preOrder();
        boolean res = tree.delete(-1);
        System.out.println("===================");
        if (res) {
            tree.preOrder();
        }
    }
}

class BinaryTree {
    private NatureNode root;

    public void setHead(NatureNode root) {
        this.root = root;
    }

    //删除结点
    public boolean delete(int id) {
        if (root == null) {
            return false;
        }
        if (root.getId() == id) {
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            } else if (root.getLeft() != null) {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
            return true;
        }
        return root.delete(id);
    }

    //后序查找
    public NatureNode postOrderSearch(int id) {
        if (root == null) {
            return null;
        }
        return root.postOrderSearch(id);
    }

    //中序查找
    public NatureNode infixOrderSearch(int id) {
        if (root == null) {
            return null;
        }
        return root.infixOrderSearch(id);
    }

    //前序查找
    public NatureNode preOrderSearch(int id) {
        if (root == null) {
            return null;
        }
        return root.preOrderSearch(id);
    }

    //后序遍历
    public void postOrder() {
        if (root == null) {
            return;
        }
        root.postOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            return;
        }
        root.infixOrder();
    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            return;
        }
        root.preOrder();
    }
}

class NatureNode {
    private int id;
    private String name;
    private NatureNode left;
    private NatureNode right;

    public NatureNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public NatureNode getLeft() {
        return left;
    }

    public void setLeft(NatureNode left) {
        this.left = left;
    }

    public NatureNode getRight() {
        return right;
    }

    public void setRight(NatureNode right) {
        this.right = right;
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

    @Override
    public String toString() {
        return "NatureNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //删除结点
    public boolean delete(int id) {
        if (this.left != null && this.left.id == id) {
            if (this.left.left == null && this.left.right == null) {
                this.left = null;
            } else if (this.left.left != null) {
                this.left = this.left.left;
            } else {
                this.left = this.left.right;
            }
            return true;
        }
        if (this.right != null && this.right.id == id) {
            if (this.right.left == null && this.right.right == null) {
                this.right = null;
            } else if (this.right.left != null) {
                this.right = this.right.left;
            } else {
                this.right = this.right.right;
            }
            return true;
        }
        boolean res = false;
        if (this.left != null) {
            res = this.left.delete(id);
        }
        if (!res) {
            if (this.right != null) {
                res = this.right.delete(id);
            }
        }
        return res;
    }

    //后序查找
    public NatureNode postOrderSearch(int id) {
        NatureNode curNode = null;
        if (this.left != null) {
            curNode = this.left.postOrderSearch(id);
        }
        if (curNode != null) {
            return curNode;
        }
        if (this.right != null) {
            curNode = this.right.postOrderSearch(id);
        }
        if (curNode != null) {
            return curNode;
        }
        System.out.println("后序查找次数~");
        if (this.id == id) {
            return this;
        }
        return null;
    }

    //中序查找
    public NatureNode infixOrderSearch(int id) {
        NatureNode curNode = null;
        if (this.left != null) {
            curNode = this.left.infixOrderSearch(id);
        }
        if (curNode != null) {
            return curNode;
        }
        System.out.println("中序查找次数~");
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            curNode = this.right.infixOrderSearch(id);
        }
        return curNode;
    }

    //前序查找
    public NatureNode preOrderSearch(int id) {
        System.out.println("前序查找次数~");
        if (this.id == id) {
            return this;
        }
        NatureNode curNode = null;
        if (this.left != null) {
            curNode = this.left.preOrderSearch(id);
        }
        if (curNode != null) {
            return curNode;
        }
        if (this.right != null) {
            curNode = this.right.preOrderSearch(id);
        }
        return curNode;
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //前序遍历
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
