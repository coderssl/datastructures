package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] array = {3, 6, 2, 1, 7, 5, 8, 9, 0, 4};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    @Test
    public static void testTime() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        System.out.println(LocalDateTime.now());
        selectSort(arr);
        System.out.println(LocalDateTime.now());
    }
}
