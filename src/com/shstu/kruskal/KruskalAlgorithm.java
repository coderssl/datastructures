package com.shstu.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author:ssl
 * @Description: datastructures
 * @Date: create in 2023/4/23 20:40
 * @Modified By:
 * @VERSON:1.8
 */
public class KruskalAlgorithm {
    private static final int INT = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INT, INT, INT, 16, 14},
                {12, 0, 10, INT, INT, 7, INT},
                {INT, 10, 0, 3, 5, 6, INT},
                {INT, INT, 3, 0, 4, INT, INT},
                {INT, INT, 5, 4, 0, 2, 8},
                {16, 7, 6, INT, 2, 0, 9},
                {14, INT, INT, INT, 8, 9, 0}
        };
        Kruskal kruskal = new Kruskal(vertex, matrix);
        kruskal.kruskal();
    }
}

class Kruskal {
    private static final int INT_MAX = Integer.MAX_VALUE;
    private final char[] vertex;
    private final int[][] matrix;
    private int numEdges;

    public Kruskal(char[] vertex, int[][] edges) {
        this.vertex = vertex;
        this.matrix = edges;
        for (int[] edge : edges) {
            for (int i : edge) {
                if (i != INT_MAX) {
                    numEdges++;
                }
            }
        }
        showEdges();
    }

    public void kruskal() {
        int[] ends = new int[numEdges];
        List<Edge> resEdges = new ArrayList<>();
        List<Edge> edges = getEdges();
        Collections.sort(edges);

        for (Edge edge : edges) {
            int start = getIndex(edge.start);
            int end = getIndex(edge.end);

            int m = getEnds(ends, start);
            int n = getEnds(ends, end);
            if (m != n) {
                ends[m] = n;
                resEdges.add(edge);
            }
        }

        showResEdges(resEdges);
    }

    public int getEnds(int[] ends, int index) {
        while (ends[index] != 0) {
            index = ends[index];
        }
        return index;
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INT_MAX) {
                    edges.add(new Edge(vertex[i], vertex[j], matrix[i][j]));
                }
            }
        }
        return edges;
    }

    public int getIndex(char c) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public void showEdges() {
        for (int[] edge : matrix) {
            for (int i : edge) {
                System.out.printf("%12d", i);
            }
            System.out.println();
        }
    }

    public void showResEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }
}

class Edge implements Comparable<Edge> {
    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "<" + start + ", " +
                end +
                "> = " + weight +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}