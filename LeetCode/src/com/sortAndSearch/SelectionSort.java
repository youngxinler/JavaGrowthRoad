package com.sortAndSearch;

public class SelectionSort implements ArraySort {
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
    }


}
