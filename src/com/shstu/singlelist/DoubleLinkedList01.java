package com.shstu.singlelist;

public class DoubleLinkedList01 {
    public DoubleNode head = new DoubleNode(0);

    public void add(DoubleNode node) {
        DoubleNode curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        curNode.next = node;
        node.pre = curNode;
    }

    public void list() {
        DoubleNode curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
            System.out.print(curNode.value + "\t");
        }
        System.out.println();
    }

    public void delete(int n) {
        DoubleNode curNode = head;
        while (curNode.next != null && n > 0) {
            curNode = curNode.next;
            n--;
        }
        if (n == 0 && curNode != head) {
            curNode.pre.next = curNode.next;
            if (curNode.next != null) {
                curNode.next.pre = curNode.pre;
            }
        }
    }

    public void update(int n, DoubleNode node) {
        DoubleNode curNode = head;
        while (curNode.next != null && n > 0) {
            curNode = curNode.next;
            n--;
        }
        if (n == 0 && curNode != head) {
            curNode.value = node.value;
        }
    }

    public int length(DoubleNode head) {
        int count = 0;
        DoubleNode node = head.next;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public void reverseDoubleNode() {
        DoubleNode curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        while (curNode.pre != null) {
            System.out.print(curNode.value + "\t");
            curNode = curNode.pre;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleLinkedList01 doubleLinkedList = new DoubleLinkedList01();
        doubleLinkedList.add(new DoubleNode(1));
        doubleLinkedList.add(new DoubleNode(2));
        doubleLinkedList.add(new DoubleNode(3));
        doubleLinkedList.add(new DoubleNode(4));
        doubleLinkedList.add(new DoubleNode(5));

        doubleLinkedList.list();
        System.out.println("======================");

//        doubleLinkedList.delete(0);
//        doubleLinkedList.list();

//        doubleLinkedList.update(5, new DoubleNode(6));
//        doubleLinkedList.list();

//        System.out.println(doubleLinkedList.length(doubleLinkedList.head));

        doubleLinkedList.reverseDoubleNode();
    }
}

class DoubleNode {
    public int value;
    public DoubleNode next;
    public DoubleNode pre;

    public DoubleNode(int value) {
        this.value = value;
    }
}
