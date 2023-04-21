package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = {3, 6, 2, 1, 7, 5, 8};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        heapSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    public static void heapSort(int[] array, int length) {
        for (int i = length / 2 - 1; i >= 0 ; i--) {
            adjustHeap(array, i, length);
        }
        int temp;
        for (int i = length - 1; i > 0; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adjustHeap(array, 0, i);
        }
    }

    public static void adjustHeap(int[] array, int i, int length) {
        int curNode = array[i];
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if (j + 1 < length && array[j] < array[j + 1]) {
                j++;
            }
            if (array[j] > curNode) {
                array[i] = array[j];
                i = j;
            }
        }
        array[i] = curNode;
    }

    @Test
    public static void testTime() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        System.out.println(LocalDateTime.now());
        heapSort(arr, arr.length);
        System.out.println(LocalDateTime.now());
    }
}
