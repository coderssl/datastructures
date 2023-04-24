package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {3, 6, 2, 1, 7, 5, 8, 9, 0};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        int piovt = array[left];

        while (l < r) {
            while (array[r] >= piovt && l < r) {
                r--;
            }
            while (array[l] <= piovt && l < r) {
                l++;
            }
            if (l < r) {
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }
        }
        //交换基准数
        array[left] = array[l];
        array[l] = piovt;
        quickSort(array, left, l - 1);
        quickSort(array, l + 1, right);
    }

    @Test
    public static void testTime() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        System.out.println(LocalDateTime.now());
        quickSort(arr, 0, arr.length - 1);
        System.out.println(LocalDateTime.now());
    }
}
