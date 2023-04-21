package com.shstu.singlelist;

@SuppressWarnings({"all"})
public class Josephu {
    private CircleNode first = null;
    private int nums;

    public Josephu(int nums) {
        this.nums = nums;
        add();
    }

    public void add() {
        CircleNode curNode = null;
        for (int i = 1; i <= nums; i++) {
            if (first == null) {
                first = new CircleNode(i);
                curNode = first;
            } else {
                CircleNode node = new CircleNode(i);
                curNode.next = node;
                node.next = first;
                curNode = node;
            }
        }
    }

    public void list() {
        CircleNode curNode = first;
        while (curNode.next != first) {
            System.out.print(curNode.value + "\t");
            curNode = curNode.next;
        }
        System.out.println(curNode.value);
    }

    public void outIndex(int start, int countNum) {
        if (start < 0 || start > nums) {
            return;
        }
        CircleNode curNode = first;
        CircleNode lastNode;
        while (curNode.next != first) {
            curNode = curNode.next;
        }
        lastNode = curNode;
        curNode = first;
        for (int i = 0; i < start - 1; i++) {
            curNode = curNode.next;
            lastNode = lastNode.next;
        }
        while (lastNode != curNode) {
            for (int i = 0; i < countNum - 1; i++) {
                curNode = curNode.next;
                lastNode = lastNode.next;
            }
            System.out.print(curNode.value + "\t");
            lastNode.next = curNode.next;
            curNode = curNode.next;
        }
        System.out.println(curNode.value);
    }

    public static void main(String[] args) {
        Josephu josephu = new Josephu(5);
        josephu.list();
        System.out.println("=========出圈顺序========");
        josephu.outIndex(1, 2);
    }
}

class CircleNode {
    public int value;
    public CircleNode next;

    public CircleNode(int value) {
        this.value = value;
    }
}
