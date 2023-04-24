package com.shstu.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author:ssl
 * @Description: datastructures
 * @Date: create in 2023/4/24 19:30
 * @Modified By:
 * @VERSON:1.8
 */
public class HorseChessBoard {
    private static int X = 8;
    private static int Y = 8;
    private static int[][] chessBoard = new int[Y][X];
    private static boolean[] visited = new boolean[X * Y];
    private static boolean finished = false;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        traversalChessBoard(2, 2, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时："  + (end - start));
        showChessBoard();
    }


    public static void traversalChessBoard(int row, int col, int step) {
        chessBoard[row][col] = step;
        visited[row * X + col] = true;
        ArrayList<Point> next = getNext(new Point(col, row));
        sort(next);
        while (!next.isEmpty()) {
            Point point = next.remove(0);
            if (!visited[point.y * X + point.x]) {
                traversalChessBoard(point.y, point.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            chessBoard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }

    public static void sort(ArrayList<Point> p) {
        p.sort(Comparator.comparingInt(o -> getNext(o).size()));
    }

    public static ArrayList<Point> getNext(Point p) {
        ArrayList<Point> list = new ArrayList<>();
        Point point = new Point();

        //5
        if((point.x = p.x - 2) >= 0 && (point.y = p.y - 1) >= 0) {
            list.add(new Point(point));
        }
        //6
        if((point.x = p.x - 1) >= 0 && (point.y = p.y - 2) >= 0) {
            list.add(new Point(point));
        }
        //7
        if((point.x = p.x + 1) < X && (point.y = p.y - 2) >= 0) {
            list.add(new Point(point));
        }
        //0
        if((point.x = p.x + 2) < X && (point.y = p.y - 1) >= 0) {
            list.add(new Point(point));
        }
        //1
        if((point.x = p.x + 2) < X && (point.y = p.y + 1) < Y) {
            list.add(new Point(point));
        }
        //2
        if((point.x = p.x + 1) < X && (point.y = p.y + 2) < Y) {
            list.add(new Point(point));
        }
        //3
        if((point.x = p.x - 1) >= 0 && (point.y = p.y + 2) < Y) {
            list.add(new Point(point));
        }
        //4
        if((point.x = p.x - 2) >= 0 && (point.y = p.y + 1) < Y) {
            list.add(new Point(point));
        }
        return list;
    }

    public static void showChessBoard() {
        for (int[] ints : chessBoard) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
