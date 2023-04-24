package com.shstu.dijkstra;

import java.util.Arrays;

/**
 * @Author:ssl
 * @Description: datastructures
 * @Date: create in 2023/4/24 15:38
 * @Modified By:
 * @VERSON:1.8
 */
public class DijkstraAlgorithm {
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
        Dijkstra dijkstra = new Dijkstra(vertex, matrix);
        dijkstra.dijkstra(0);
        dijkstra.showMatrix();
        dijkstra.showDist();
    }
}

class Dijkstra {
    private char[] vertex;
    private int[][] matrix;
    private int[] dist;
    private boolean[] visited;
    private static final int MAX = Integer.MAX_VALUE;

    public Dijkstra(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        dist = new int[vertex.length];
        visited = new boolean[vertex.length];
    }

    public void dijkstra(int source) {
        Arrays.fill(dist, MAX);
        dist[source] = 0;

        for (int i = 1; i < vertex.length; i++) {

            int c = minDistance();
            if (c == -1) {
                return;
            }
            visited[c] = true;

            for (int j = 0; j < vertex.length; j++) {
                if (!visited[j] && matrix[c][j] != MAX && dist[c] + matrix[c][j] < dist[j]) {
                    dist[j] = dist[c] + matrix[c][j];
                }
            }
        }
    }

    public int minDistance() {
        int min = MAX;
        int minIndex = -1;
        for (int i = 0; i < vertex.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void showDist() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                System.out.print("<" + vertex[i] + ", " + vertex[j] + "> = " + dist[j] + "\t");
            }
            System.out.println();
        }
    }

    public void showMatrix() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
