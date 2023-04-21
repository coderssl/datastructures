package com.shstu.recursion;

public class MiGong {
    public int[][] map = new int[8][7];

    public MiGong() {
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int j = 0; j < map[0].length; j++) {
            map[0][j] = 1;
            map[7][j] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
    }

    public void print() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public boolean setWay(int i, int j) {
        if (map[4][1] == 2) {
            return true;
        } else if (map[i][j] == 0) {
            map[i][j] = 2;
            if (setWay(i, j + 1)) {
                return true;
            } else if (setWay(i + 1, j)) {
                return true;
            } else if (setWay(i, j - 1)) {
                return true;
            } else if (setWay(i - 1, j)) {
                return true;
            } else {
                map[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        MiGong miGong = new MiGong();
        miGong.print();
        System.out.println("================");
        miGong.setWay(1, 1);
        miGong.print();
    }
}
