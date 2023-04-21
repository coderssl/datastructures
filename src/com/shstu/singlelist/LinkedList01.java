package com.shstu.singlelist;


public class LinkedList01 {
    public Node head = new Node(0);

    public void add(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void delete(int n) {
        Node temp = head;
        n--;
        while (temp.next != null && n > 0) {
            n--;
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public void update(int n, Node node) {
        Node temp = head;
        while (temp.next != null && n > 0) {
            n--;
            temp = temp.next;
//            System.out.println("n = " + n);
//            System.out.println("value = " + temp.value);
        }
        if (n == 0) {
            temp.value = node.value;
        }
    }

    public void list() {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.print(temp.value + "\t");
        }
        System.out.println();
    }

    public void sort() {
        Node curNode = head.next;
        Node nextNode;
        while (curNode.next != null) {
            nextNode = curNode.next;
            while (nextNode != null) {
                if (curNode.value > nextNode.value) {
                    int temp = nextNode.value;
                    nextNode.value = curNode.value;
                    curNode.value = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
    }

    public int length(Node head) {
        int count = 0;
        Node node = head.next;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public Node findLastIndexNode(int index) {
        int length = length(head);
        index = length - index;
        Node node = head.next;
        while (node != null && index > 0) {
            index--;
            node = node.next;
        }
        return node;
    }

    public void reverseNode() {
        Node node = new Node(0);
        Node curNode = head.next;
        while (curNode != null) {
           Node temp = curNode.next;
           curNode.next = node.next;
           node.next = curNode;
           curNode = temp;
        }
        head = node;
    }

    public void reversePrint()  {
        int length = length(head) - 1;
        Node curNode = head.next;
        if (curNode == null) {
            return;
        }
        while (length > 0) {
            for (int i = 0; i < length; i++) {
                curNode = curNode.next;
            }
            System.out.print(curNode.value + "\t");
            length--;
            curNode = head.next;
        }
        System.out.println(curNode.value);
    }
    public LinkedList01 combineList(LinkedList01 list) {
        LinkedList01 newList = new LinkedList01();
        int length1 = length(head);
        int length2 = list.length(list.head);
        if (length1 > length2) {
            Node curNode = head;
            while (curNode.next != null) {
                curNode = curNode.next;
            }
            curNode.next = list.head.next;
            sort();
            newList.head = head;
        } else {
            Node curNode = list.head;
            while (curNode.next != null) {
                curNode = curNode.next;
            }
            curNode.next = head.next;
            list.sort();
            newList.head = list.head;
        }
        return newList;
    }

    public static void main(String[] args) {
        LinkedList01 linkedList = new LinkedList01();
        linkedList.add(new Node(5));
        linkedList.add(new Node(4));
        linkedList.add(new Node(3));
        linkedList.add(new Node(2));
        linkedList.add(new Node(1));
        linkedList.add(new Node(-100));

        LinkedList01 linkedList1 = new LinkedList01();
        linkedList1.add(new Node(6));
        linkedList1.add(new Node(7));
        linkedList1.add(new Node(8));
        linkedList1.add(new Node(9));
        linkedList1.add(new Node(10));

        //linkedList.list();

        //linkedList.update(5, new Node(6));
        //linkedList.list();

        //linkedList.delete(5);
        //linkedList.list();

//        linkedList.sort();
//        linkedList.list();

//        Node node = linkedList.findLastIndexNode(3);
//        System.out.println(node.value);

//        linkedList.reverseNode();
        //linkedList.list();

        //linkedList.reversePrint();

        LinkedList01 combineList = linkedList.combineList(linkedList1);
        combineList.list();
        System.out.println(combineList.head == linkedList.head);


    }
}

class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }
}
