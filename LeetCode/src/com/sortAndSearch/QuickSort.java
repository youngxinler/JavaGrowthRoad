package com.sortAndSearch;

import java.util.Arrays;

/**
 * @author youngxinler  19-6-18 下午7:49
 * @version 0.1
 **/

public class QuickSort {

    public static void main(String[] args) {
        int[] test = new int[]{2, 5, 8, 4, 6, 7, 9, 1, 3};
        sort(test);
        System.out.println(Arrays.toString(test));
    }

    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int standard = partition(nums, left, right);
            quickSort(nums, left, standard - 1);
            quickSort(nums, standard + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (nums[i] > nums[left]) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, left, index - 1);
        return index - 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
