package com.shstu.search;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        int target = 1;
        int index = binarySearch(array, 0, array.length - 1, target);
        System.out.println(index);
    }

    public static int binarySearch(int[] array, int left, int right, int target) {
        //递归
//        if (left > right || target < array[left] || target > array[right]) {
//            return -1;
//        }
//        int mid = left + (right - left) / 2;
//        int midNum = array[mid];
//        if (target < midNum) {
//            return binarySearch(array, left, mid - 1, target);
//        } else if (target > midNum) {
//            return binarySearch(array, mid + 1, right, target);
//        } else {
//            return mid;
//        }

        //非递归
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = array[mid];
            if (target < midNum) {
                right = mid - 1;
            } else if (target > midNum) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
