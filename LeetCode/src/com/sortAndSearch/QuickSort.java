package com.sortAndSearch;

/**
 * @author youngxinler  19-6-18 下午7:49
 * @version 0.1
 **/

public class QuickSort implements IArraySort {
    @Override
    public int[] sort(int[] nums) {
        return quickSort(nums, 0, nums.length - 1);
    }

    private int[] quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int mid = partition(nums, l, r);
            quickSort(nums, l, mid);
            quickSort(nums, mid + 1, r);
        }
        return nums;
    }

    private int partition(int[] nums, int l, int r) {
        int index = l + 1;
        for (int i = index; i <= r; i++) {
            if (nums[i] < nums[l]) {
                swap(nums, index, i);
                index++;
            }
        }
        swap(nums, index - 1, l);
        return index - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
