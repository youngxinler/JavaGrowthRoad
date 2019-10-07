package com.sortAndSearch;

public class ShellSort implements ArraySort {
    @Override
    public void sort(int[] nums) {
        int gap = 1;
        int len = nums.length;
        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int j = i - gap;
                int temp = nums[i];
                while (j >= 0 && nums[j] > temp) {
                    nums[j + gap] = nums[j];
                    j -= gap;
                }
                nums[j + gap] = temp;
            }
            gap = (int) Math.floor(gap / 3);
        }
    }
}
