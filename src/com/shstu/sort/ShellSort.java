package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;


public class ShellSort {
    public static void main(String[] args) {
        int[] array = {3, 6, 2, 1, 7, 5, 8, 9, 0};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void shellSort(int[] array) {
        int curNum;
        int index;
        int count = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                curNum = array[i];
                index = i - gap;
                if (array[index] > curNum) {
                    while (index >= 0 && array[index] > curNum) {
                        array[index + gap] = array[index];
                        index -= gap;
                    }
                    array[index + gap] = curNum;
                }
            }
            System.out.printf("第%d次排序", ++count);
            System.out.println(Arrays.toString(array));
        }
    }

    @Test
    public static void testTime() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        System.out.println(LocalDateTime.now());
        shellSort(arr);
        System.out.println(LocalDateTime.now());
    }
}
