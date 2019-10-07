package com.sortAndSearch;

public class SelectionSort implements ArraySort {
    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int pos = i;
            int target = nums[pos];
            while (pos > 0 && nums[pos - 1] > target) {
                nums[pos] = nums[pos - 1];
                pos--;
            }
            if (pos != i) {
                nums[pos] = target;
            }
        }
    }
}
