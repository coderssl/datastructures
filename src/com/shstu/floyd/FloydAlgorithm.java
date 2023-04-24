package com.shstu.floyd;

/**
 * @Author:ssl
 * @Description: datastructures
 * @Date: create in 2023/4/24 16:17
 * @Modified By:
 * @VERSON:1.8
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        int MAX = Integer.MAX_VALUE;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, MAX, MAX, MAX, 16, 14},
                {12, 0, 10, MAX, MAX, 7, MAX},
                {MAX, 10, 0, 3, 5, 6, MAX},
                {MAX, MAX, 3, 0, 4, MAX, MAX},
                {MAX, MAX, 5, 4, 0, 2, 8},
                {16, 7, 6, MAX, 2, 0, 9},
                {14, MAX, MAX, MAX, 8, 9, 0},
        };
        Floyd floyd = new Floyd(vertex, matrix);
        floyd.floyd();
        floyd.showMatrix();
        floyd.showPath();
    }
}

class Floyd {
    private char[] vertex;
    private int[][] matrix;
    private int[][] path;
    private static final int MAX = Integer.MAX_VALUE;

    public Floyd(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        path = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                if (matrix[i][j] != MAX) {
                    path[i][j] = j;
                }
            }
        }
    }

    public void floyd() {
        for (int k = 0; k < vertex.length; k++) {//中间顶点
            for (int i = 0; i < vertex.length; i++) {//出发顶点
                for (int j = 0; j < vertex.length; j++) {//终点
                    if (matrix[i][k] != MAX && matrix[j][k] != MAX && matrix[i][k] + matrix[j][k] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[j][k];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
    }

    public void showMatrix() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print("<" + vertex[i] + ", " + vertex[j] + "> = " + matrix[i][j] + "\t\t");
            }
            System.out.println();
        }
    }

    public void showPath() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                System.out.print("从" + i + "到" + j + "的最短路径为：" + i);
                int k = path[i][j];
                while (k != j) {
                    System.out.print(" -> " + k);
                    k = path[k][j];
                }
                System.out.println(" -> " + j + "，路径长度为：" + matrix[i][j]);
            }
        }
    }
}