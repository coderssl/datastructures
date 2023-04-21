package com.shstu.recursion;

public class Test {
    public static void main(String[] args) {
        System.out.println(f(1));
    }


    public static int f(int n) {
        if (n <= 1) {
            return 1;
        }
        return f(n - 1) * n;
    }
}
