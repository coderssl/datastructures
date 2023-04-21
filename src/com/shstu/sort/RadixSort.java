package com.shstu.sort;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] array = {3000, 6, 2, 1, 7, 5, 8, 9, 0};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void radixSort(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][array.length];
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            for (int j = 0; j < array.length; j++) {
                int digitOfElement = array[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                if (bucketElementCounts[j] != 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        array[index++] = bucket[j][k];
                    }
                    bucketElementCounts[j] = 0;
                }
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
        radixSort((arr));
        System.out.println(LocalDateTime.now());
    }
}
