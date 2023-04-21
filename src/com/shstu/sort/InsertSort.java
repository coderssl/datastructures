package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] array = {3, 6, 2, 1, 7, 5, 8, 9, 0, 4};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void insertSort(int[] array) {
        int curNum;
        int index;
        for (int i = 1; i < array.length; i++) {
            curNum = array[i];
            index = i - 1;
            while (index >= 0 && array[index] > curNum) {
                array[index + 1] = array[index];
                index--;
            }
            if (index + 1 != i) {
                array[index + 1] = curNum;
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
        insertSort(arr);
        System.out.println(LocalDateTime.now());
    }
}
