package com.shstu.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    private int[] array;
    private int front;
    private int rear;
    private int maxsize;

    public CircleArrayQueue(int n) {
        maxsize = n;
        array = new int[maxsize];
    }

    public boolean isFull() {
        return (rear + 1) % maxsize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int num) {
        if (isFull()) {
            throw new RuntimeException("环形队列已满!");
        }
        array[rear] = num;
        rear = (rear + 1) % maxsize;
    }

    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("环形队列为空!");
        }
        int temp = array[front];
        front = (front + 1) % maxsize;
        return temp;
    }

    public void showQueue() {
        for (int i = front; i < front + getSize(); i++) {
            System.out.print(array[i % maxsize]);
        }
        System.out.println();
    }

    public int getSize() {
        return (maxsize - front + rear) % maxsize;
    }

    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("环形队列为空!");
        }
        return array[front];
    }

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.print("输出一个数：");
                    try {
                        queue.addQueue(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据是：" + queue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("队列头的数据是：" + queue.getHead());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
