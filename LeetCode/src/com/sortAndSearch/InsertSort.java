package com.sortAndSearch;

public class InsertSort implements ArraySort {
    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            //记录要排序的数字
            int temp = nums[i];

            int j = i;
            //往后与排序后的进行一一比较
            while (j > 0 && temp < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }

            if (j != i) {
                nums[j] = temp;
            }
        }
    }
}
