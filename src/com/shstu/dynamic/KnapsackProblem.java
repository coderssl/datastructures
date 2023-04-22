package com.shstu.dynamic;

/**
 * 01背包问题
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int[] weight = {2, 4, 1};
        int[] value = {2000, 3000, 4000};
        int bagWeight = 6;
        int res = knapsackProblem(weight, value, bagWeight);
        System.out.println(res);
    }

    public static int knapsackProblem(int[] weight, int[] value, int bagWeight) {
        int[][] db = new int[weight.length + 1][bagWeight + 1];
        for (int i = 0; i < db.length; i++) {
            db[i][0] = 0;
        }
        for (int i = 0; i <= bagWeight; i++) {
            db[0][i] = 0;
        }
        for (int i = 1; i < db.length; i++) {
            for (int j = 1; j <= bagWeight; j++) {
                if (weight[i - 1] <= j) {
                    db[i][j] = Math.max(db[i - 1][j], value[i - 1] + db[i - 1][j - weight[i - 1]]);
                } else {
                    db[i][j] = db[i - 1][j];
                }
            }
        }
        return db[weight.length][bagWeight];
    }
}
