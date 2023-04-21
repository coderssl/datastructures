package com.shstu.recursion;

import java.util.LinkedList;
import java.util.Queue;

public class EightQueens {
    public static int[] queens = new int[8];
    public static int count = 0;

    public static void eightQueens(int row) {
        if (row == 8) {
            count++;
            print();
            return;
        }
        for (int i = 0; i < queens.length; i++) {
            queens[row] = i;
            if (isValid(row)) {
                eightQueens( row + 1);
            }
        }
    }

    public static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == queens[row] || Math.abs(queens[i] - queens[row]) == row - i) {
                return false;
            }
        }
        return true;
    }

    public static void print() {
        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < queens.length; j++) {
                if (queens[i] == j) {
                    System.out.print("Q");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("====================");
    }

    public static void main(String[] args) {
        eightQueens(0);
        System.out.println(count);
    }
}
