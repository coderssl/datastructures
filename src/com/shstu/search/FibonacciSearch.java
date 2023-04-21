package com.shstu.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static final int MAX_SIZE = 20;

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        int target = 3;
        int index = fibonacciSearch(array, 0, array.length - 1, target);
        System.out.println(index);
    }

    public static int fibonacciSearch(int[] array, int left, int right, int target) {
        int[] F = fibonacci();
        int k =0;
        while (array.length > F[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(array, F[k]);
        for (int i = array.length; i < temp.length; i++) {
            temp[i] = array[right];
        }
        int mid;
        int midNum;
        while (left <= right) {
            mid = left + F[k - 1] - 1;
            midNum = temp[mid];
            if (target < midNum) {
                right = mid - 1;
                k--;
            } else if (target > midNum) {
                left = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, right);
            }
        }
        return -1;
    }

    public static int[] fibonacci() {
        int[] F = new int[MAX_SIZE];
        F[0] = 1;
        F[1] = 1;
        for (int i = 2; i < MAX_SIZE; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }
        return F;
    }
}
