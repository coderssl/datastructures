package com.shstu.prim;

import java.util.Arrays;

/**
 * @Author:ssl
 * @Description: datastructures
 * @Date: create in 2023/4/23 17:26
 * @Modified By:
 * @VERSON:1.8
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = {
                //A     B  C    D      E      F    G
                {10000, 5, 7, 10000, 10000, 10000, 2},//    A
                {5, 10000, 10000, 9, 10000, 10000, 3},//    B
                {7, 10000, 10000, 10000, 8, 10000, 10000},//C
                {10000, 9, 10000, 10000, 10000, 4, 10000},//D
                {10000, 10000, 8, 10000, 10000, 5, 4},//    E
                {10000, 10000, 10000, 4, 5, 10000, 6},//    F
                {2, 3, 10000, 10000, 4, 6, 10000}//         G
        };

        MinTree minTree = new MinTree(vertex, weight, data);
        minTree.showGraph();
        minTree.prim(0);
    }
}

class MinTree {
    private MGraph graph;

    public MinTree(int vertex, int[][] weight, char[] data) {
        graph = new MGraph(vertex);
        graph.data = data;
        graph.weight = weight;
    }

    public void prim(int v) {
        int[] visited = new int[graph.vertex];
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minEdge = 10000;
        int count = 0;
        for (int k = 1; k < graph.vertex; k++) {
            for (int i = 0; i < graph.vertex; i++) {
                if (visited[i] == 1) {
                    for (int j = 0; j < graph.vertex; j++) {
                        if (visited[j] == 0 && graph.weight[i][j] < minEdge) {
                            h1 = i;
                            h2 = j;
                            minEdge = graph.weight[i][j];
                        }
                    }
                }
            }
            visited[h2] = 1;
            minEdge = 10000;
            if (h1 != -1) {
                System.out.println("第" + (++count) + "条边 <" + graph.data[h1] + ", " + graph.data[h2] + "> = " + graph.weight[h1][h2]);
            }
        }
    }

    public void showGraph() {
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

class MGraph {
    int vertex;
    int[][] weight;
    char[] data;

    public MGraph(int vertex) {
        this.vertex = vertex;
        weight = new int[vertex][vertex];
        data = new char[vertex];
    }
}
