package com.shstu.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array = {2, 2, 2, 2, 2, 2};
        System.out.println(Arrays.toString(array));
        System.out.println("====================");
        int target = 2;
        int index = insertValueSearch(array, 0, array.length - 1, target);
        System.out.println(index);
    }

    public static int insertValueSearch(int[] array, int left, int right, int target) {
        //递归
//        if (left > right || target < array[left] || target > array[right]) {
//            return -1;
//        }
//        if (array[left] == array[right]) {
//            if (target == array[left]) {
//                return left;
//            } else {
//                return -1;
//            }
//        }
//        int mid = left + (right - left) * (target - array[left]) / (array[right] - array[left]);
//        int midNum = array[mid];
//        if (target < midNum) {
//            return insertValueSearch(array, left, mid - 1, target);
//        } else if (target > midNum) {
//            return insertValueSearch(array, mid + 1, right, target);
//        } else {
//            return mid;
//        }

        //非递归
        if (array[left] == array[right]) {
            if (target == array[left]) {
                return left;
            } else {
                return -1;
            }
        }
        while (left <= right) {
            int mid = left + (right - left) * (target - array[left]) / (array[right] - array[left]);
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
