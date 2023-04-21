package com.shstu.tree;


public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] array = {7,4,12,11,13,10};
        for (int i : array) {
            avlTree.add(i);
        }
        avlTree.infixOrder();
        System.out.println();
        System.out.println("AVL树的高度是：" + avlTree.height() + " 左子树的高度是：" + avlTree.leftHeight() + " 右子树的高度是：" + avlTree.rightHeight());
        System.out.println("根节点是：" + avlTree.getRoot());

        //avlTree.delete(10);
//        avlTree.delete(12);
//        avlTree.delete(6);
//        avlTree.delete(5);

        avlTree.delete(12);
        avlTree.delete(13);

        avlTree.infixOrder();
        System.out.println();
        System.out.println("AVL树的高度是：" + avlTree.height() + " 左子树的高度是：" + avlTree.leftHeight() + " 右子树的高度是：" + avlTree.rightHeight());
        System.out.println("根节点是：" + avlTree.getRoot());
        System.out.println("根节点是：" + avlTree.getRoot());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void delete(int value) {
        Node targetNode = root.search(root, value);
        Node parentNode = root.parentNode(value);

        if (root == null || targetNode == null) {
            return;
        }

        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            if (parentNode != null) {
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(null);
                } else {
                    parentNode.setRight(null);
                }
            } else {
                root = null;
            }
        } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
            Node minRightNode = minRightNode(targetNode.getRight());
            int temp = minRightNode.getValue();
            //delete(minRightNode.getValue());
            Node pNode = root.parentNode(temp);
            if (pNode.getLeft() != null && pNode.getLeft().getValue() == temp) {
                pNode.setLeft(null);
            } else {
                pNode.setRight(null);
            }
            targetNode.setValue(temp);
        } else {
            if (root.getValue() == targetNode.getValue()) {
                if (root.getLeft() != null) {
                    root = root.getLeft();
                } else {
                    root = root.getRight();
                }
            } else if (targetNode.getLeft() != null) {
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(targetNode.getLeft());
                } else {
                    parentNode.setRight(targetNode.getLeft());
                }
            } else {
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(targetNode.getRight());
                } else {
                    parentNode.setRight(targetNode.getRight());
                }
            }
        }
        if (leftHeight() - rightHeight() > 1) {
            if (root.getLeft() != null && root.getLeft().rightHeight() > root.getLeft().leftHeight()) {
                root.getLeft().RRRotate();
            }
            root.LLRotate();
            return;
        }
        if (rightHeight() - leftHeight() > 1) {
            if (root.getRight() != null && root.getRight().leftHeight() > root.getRight().rightHeight()) {
                root.getRight().LLRotate();
            }
            root.RRRotate();
        }
    }

    public Node minRightNode(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public int rightHeight() {
        if (root == null) {
            return 0;
        }
        return root.rightHeight();
    }

    public int leftHeight() {
        if (root == null) {
            return 0;
        }
        return root.leftHeight();
    }

    public int height() {
        if (root == null) {
            return 0;
        }
        return root.height();
    }

    public void infixOrder() {
        if (root == null) {
            return;
        }
        root.infixOrder();
    }

    public void search(int value) {
        Node node = root.search(root, value);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println("没有找到该结点~");
        }
    }

    public void add(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }
}

class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return value + "";
    }

    public Node parentNode(int value) {
        if ((left != null && left.value == value) || (right != null && right.value == value)) {
            return this;
        } else if (left != null && value < this.value) {
            return left.parentNode(value);
        } else if (right != null && value > this.value) {
            return right.parentNode(value);
        } else {
            return null;
        }
    }

    public Node search(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            return search(node.left, value);
        } else if (value > node.value) {
            return search(node.right, value);
        } else {
            return node;
        }
    }

    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.print(this + "\t");
        if (right != null) {
            right.infixOrder();
        }
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        Node curNode = this;
        while (true) {
            if (node.value < curNode.value) {
                if (curNode.left == null) {
                    curNode.left = node;
                    break;
                } else {
                    curNode = curNode.left;
                }
            } else {
                if (curNode.right == null) {
                    curNode.right = node;
                    break;
                } else {
                    curNode = curNode.right;
                }
            }
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.RRRotate();
            }
            LLRotate();
            return;
        }
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.LLRotate();
            }
            RRRotate();
        }
    }

    public void RRRotate() {
        Node curNode = new Node(value);
        curNode.left = left;
        curNode.right = right.left;
        value = right.value;
        right = right.right;
        left = curNode;
    }

    public void LLRotate() {
        Node curNode = new Node(value);
        curNode.right = right;
        curNode.left = left.right;
        value = left.value;
        left = left.left;
        right = curNode;
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0: right.height()) + 1;
    }
}
