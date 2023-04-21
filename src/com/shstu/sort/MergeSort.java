package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = {3, 6, 2, 1, 7, 5, 8, 9, 0};
        int[] temp = new int[array.length];
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[index] = array[i];
                index++;
                i++;
            } else {
                temp[index] = array[j];
                index++;
                j++;
            }
        }
        while (i <= mid) {
            temp[index] = array[i];
            index++;
            i++;
        }
        while (j <= right) {
            temp[index] = array[j];
            index++;
            j++;
        }
        index = 0;
        for (int k = left; k <= right; k++) {
            array[k] = temp[index++];
        }
    }

    @Test
    public static void testTime() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[arr.length];
        System.out.println(LocalDateTime.now());
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(LocalDateTime.now());
    }
}
