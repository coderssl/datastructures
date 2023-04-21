package com.shstu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        String[] vertex = {"1","2","3","4","5","6","7","8"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(3, 7, 1);
        graph.insertEdges(4, 7, 1);
        graph.insertEdges(2, 5, 1);
        graph.insertEdges(2, 6, 1);
        graph.insertEdges(5, 6, 1);


        graph.DFS();
        System.out.println();
        graph.BFS();
    }
}

class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private boolean[] isVisited;
    private int numOfEdges;

    public Graph(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
    }

    public void BFS() {
        int size = getNumOfVertex();
        isVisited = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!isVisited[i]) {
                BFS(i);
            }
        }
    }

    public void BFS(int v) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(v);
        isVisited[v] = true;
        System.out.print(getValueByIndex(v) + "->");
        while (!queue.isEmpty()) {
            int first = queue.remove();
            int next = getFirstNeighbor(first);
            while (next != -1) {
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    queue.add(next);
                    System.out.print(getValueByIndex(next) + "->");
                }
                next = getNextNeighbor(first, next);
            }
        }
    }

    public void DFS() {
        int size = getNumOfVertex();
        isVisited = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!isVisited[i]) {
                DFS(i);
            }
        }
    }

    public void DFS(int v) {
        isVisited[v] = true;
        System.out.print(getValueByIndex(v) + "->");
        int w = getFirstNeighbor(v);
        while (w != -1) {
            if (!isVisited[w]) {
                DFS(w);
            }
            w = getNextNeighbor(v, w);
        }
    }

    public int getNextNeighbor(int v, int w) {
        int size = getNumOfVertex();
        for (int i = w + 1; i < size; i++) {
            if (edges[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public int getFirstNeighbor(int v) {
        int size = getNumOfVertex();
        for (int i = 0; i < size; i++) {
            if (edges[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public void showEdges() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
