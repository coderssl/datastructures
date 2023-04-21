package com.shstu.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public LinkedList[] list;
    public int size;

    public static void main(String[] args) {
        HashTableDemo hashTable = new HashTableDemo(6);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("add");
            System.out.println("query");
            System.out.println("delete");
            System.out.println("list");
            System.out.println("exit");
            System.out.print("请输入你的选择:");
            String s = scanner.next();
            switch (s) {
                case "add":
                    System.out.print("id = ");
                    int id = scanner.nextInt();
                    System.out.print("name = ");
                    String name = scanner.next();
                    hashTable.add(new Student(id, name));
                    break;
                case "query":
                    System.out.print("id = ");
                    id = scanner.nextInt();
                    hashTable.query(id);
                    break;
                case "delete":
                    System.out.print("id = ");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                case "list":
                    hashTable.printList();
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }

    public HashTableDemo(int size) {
        this.size = size;
        this.list = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            list[i] = new LinkedList();
        }
    }

    public void printList() {
        for (int i = 0; i < size; i++) {
            if (list[i].head == null) {
                System.out.printf("第%d条链表为空~\n", i);
            } else {
                list[i].list();
            }
        }
    }

    public void delete(int id) {
        boolean res = list[fun(id)].delete(id);
        if (res) {
            System.out.println("删除成功~");
        } else {
            System.out.println("删除失败~");
        }
    }

    public void query(int id) {
        Student student = list[fun(id)].query(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("找不到该学生~");
        }
    }

    public void add(Student stu) {
        list[fun(stu.id)].add(stu);
    }

    public int fun(int id) {
        return id % size;
    }
}

class LinkedList {
    public Student head;

    public void add(Student stu) {
        if (head == null) {
            head = stu;
            return;
        }
        Student curNode = head;
        while (curNode.next != null && curNode.id < stu.id) {
            curNode = curNode.next;
        }
        if (head.id > stu.id) {
            stu.next = head;
            head = stu;
            return;
        }
        stu.next = curNode.next;
        curNode.next = stu;
    }

    public Student query(int id) {
        Student curNode = head;
        while (curNode != null) {
            if (curNode.id == id) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    public boolean delete(int id) {
        if (head == null) {
            return false;
        } else if (head.id == id) {
            head = head.next;
            return true;
        }
        Student curNode = head;
        while (curNode.next != null) {
            if (curNode.next.id == id) {
                curNode.next = curNode.next.next;
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    public void list() {
        Student curNode = head;
        while (curNode != null) {
            System.out.print(curNode);
            curNode = curNode.next;
        }
        System.out.println();
    }
}

class Student {
    public int id;
    public String name;
    public Student next;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "-> id = " + id + " name = " + name + " ";
    }
}
