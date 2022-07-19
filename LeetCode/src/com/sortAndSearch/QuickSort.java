package com.sortAndSearch;

import java.util.Arrays;

/**
 * @author youngxinler  19-6-18 下午7:49
 * @version 0.1
 **/

public class QuickSort{

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[]{1,23,4,6,0,9};
        quickSort.sort(nums);
        System.out.println(Arrays.toString(nums));
        int[] nums2 = new int[]{1,23,4,6,0,9};
        quickSort.quickSort3Ways(nums2, 0, nums2.length);
        System.out.println(Arrays.toString(nums2));
    }

    public void quickSort3Ways(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        //{1,23,4,6,0,9};
        int mid = arr[l];
        int lt = l;
        int rt = r;
        int i = l + 1;
        while (i < rt) {
            if (arr[i] < mid) {
                swap(arr, i, lt + 1);
                lt++;
                i++;
            } else if (arr[i] > mid) {
                swap(arr, i, rt - 1);
                rt--;
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        quickSort3Ways(arr, l, lt - 1);
        quickSort3Ways(arr, rt, r);
    }

    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int l, int r){
        if (l < r){
            int mid = partition(nums, l, r);
            quickSort(nums, l, mid - 1);
            quickSort(nums, mid + 1, r);
        }
    }

    private int partition(int[] nums, int l, int r){
        int index = l + 1;
        for(int i = index; i <= r; i++){
            if (nums[l] > nums[i]){
                swap(nums, i, index++);
            }
        }
        swap(nums, l, index - 1);
        return index - 1;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
