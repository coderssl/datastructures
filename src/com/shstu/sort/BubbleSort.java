package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 6, 9, 10, 8, 20, 0};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        boolean loop = true;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    loop = false;
                }
            }
            if (loop) {
                break;
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
        bubbleSort(arr);
        System.out.println(LocalDateTime.now());
    }
}
