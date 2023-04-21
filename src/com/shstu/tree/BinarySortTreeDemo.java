package com.shstu.tree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        int[] array = {7, 3, 9, 1, 4, 8, 12, 10, 11};
        for (int i : array) {
            tree.add(i);
        }
        tree.infixOrder();
        //tree.query(1);

        System.out.println();


//        tree.delete(9);
//        tree.delete(10);
//        tree.delete(11);
//        tree.delete(1);
//        tree.delete(7);
//        tree.delete(3);
//        tree.delete(4);
//        tree.delete(12);
//        tree.delete(8);

        tree.infixOrder();
    }
}

class BinarySortTree {
    private TreeNode root;

    public void delete(int value) {
        TreeNode targetNode = root.query(root, value);
        TreeNode parentNode = root.parentNode(value);

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
            TreeNode minRightNode = minRightNode(targetNode.getRight());
            int temp = minRightNode.getValue();
            delete(minRightNode.getValue());
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
    }

    public TreeNode minRightNode(TreeNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void infixOrder() {
        if (root == null) {
            return;
        }
        root.infixOrder();
    }

    public void query(int value) {
        TreeNode query = root.query(root, value);
        if (query != null) {
            System.out.println(query);
        } else {
            System.out.println("没有找到该结点~");
        }
    }

    public void add(int value) {
        TreeNode node = new TreeNode(value);
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }
}

class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return value + "";
    }

    public TreeNode parentNode(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else if (this.left != null && value < this.value) {
            return this.left.parentNode(value);
        } else if (this.right != null && value > this.value) {
            return this.right.parentNode(value);
        } else {
            return null;
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.print(this + "\t");
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void add(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode curNode = this;
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
    }

    public TreeNode query(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            return query(node.left, value);
        } else if (value > node.value) {
            return query(node.right, value);
        } else {
            return node;
        }
    }
}
