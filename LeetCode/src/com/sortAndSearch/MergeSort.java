package com.sortAndSearch;

import java.util.Arrays;

public class MergeSort implements IArraySort {
    @Override
    public int[] sort(int[] nums) {
        return innerSort(nums);
    }

    private int[] innerSort(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums;
        }
        int middle = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, middle);
        int[] right = Arrays.copyOfRange(nums, middle, len);
        return merge(innerSort(left), innerSort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int l = 0;
        int r = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                result[i++] = left[l++];
            } else {
                result[i++] = right[r++];
            }
        }
        if (l < left.length) {
            System.arraycopy(left, l, result, i, left.length - l);
        }
        if (r < right.length) {
            System.arraycopy(right, r, result, i, right.length - r);
        }
        return result;
    }
}
