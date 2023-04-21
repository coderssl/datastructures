package com.shstu.stack_;


import java.util.ArrayList;
import java.util.List;

public class ListStack {
    private ListNode top = null;

    public void push(ListNode node) {
        if (top != null) {
            node.next = top;
        }
        top = node;
    }

    public void list() {
        ListNode curNode = top;
        while (curNode != null) {
            System.out.print(curNode.value + "\t");
            curNode = curNode.next;
        }
        System.out.println();
    }

    public void pop() {
        if (top == null) {
            return;
        }
        System.out.println(top.value);
        top = top.next;
    }

    public static void main(String[] args) {
        ListStack listStack = new ListStack();
        listStack.push(new ListNode(1));
        listStack.push(new ListNode(2));
        listStack.push(new ListNode(3));
        listStack.push(new ListNode(4));
        listStack.push(new ListNode(5));

        listStack.list();
        System.out.println("=================");

        listStack.pop();
        listStack.list();

    }
}

class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }
}
