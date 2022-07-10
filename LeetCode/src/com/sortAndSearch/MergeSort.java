package com.sortAndSearch;

import java.util.Arrays;

public class MergeSort{


    public static void main(String[] args) {
        int[] nums = new int[]{9,3,5,1,53,31,5};
        new MergeSort().sortFromBottom(nums);
        System.out.println(Arrays.toString(nums));
    }


    private int[] temp;

    public void mergeSort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    public void merge(int[] nums, int l, int mid, int r) {
        int i = l, j = mid + 1;

        //        if (r + 1 - l >= 0) System.arraycopy(nums, l, temp, l, r + 1 - l);
        for (int k = l; k <= r; k++) {
            temp[k] = nums[k];
        }


        // can replace with this code
//        for (int k = l; k <= r; k++) {
//            if (i > mid) nums[k] = temp[j++];
//            else if (j > r) nums[k] = temp[i++];
//            else if (nums[i] > nums[j]) nums[k] = temp[i++];
//            else nums[k] = nums[j++];
//        }

        int k = l;
        while(i <= mid && j <= r) {
            if (temp[i] > temp[j]) {
                nums[k++] = temp[i++];
            } else {
                nums[k++] = temp[j++];
            }
        }

        while(i <= mid) nums[k++] = temp[i++];
        while(j <= r) nums[k++] = temp[j++];
    }

    public void sort(int[] nums, int l, int r) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    public void sortFromBottom(int[] nums) {
        this.temp = new int[nums.length];
        int n = nums.length;
        for (int len = 1; len < n; len = len * 2) {
            // len 为子数组的长度， 对子数组进行排序， 然后再合并
            for (int l = 0; l < n - len; l += len * 2) {
                merge(nums, l, l + len - 1, Math.min(n - 1, l + 2 * len - 1));
            }
        }
    }
}
