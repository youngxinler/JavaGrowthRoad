package com.sortAndSearch;

public class ShellSort implements ArraySort {
    @Override
    public void sort(int[] nums) {
        int gap = 1;
        int len = nums.length;
        while (gap < nums.length / 3) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int tmp = nums[i];
                int j = i - gap;
                while (j >= 0 && nums[j] > tmp) {
                    nums[j + gap] = nums[j];
                    j -= gap;
                }
                nums[j + gap] = tmp;
            }
            gap /= 3;
        }
    }
}
