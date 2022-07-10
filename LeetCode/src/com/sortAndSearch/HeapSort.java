package com.sortAndSearch;

import java.util.Arrays;

//堆排序是一种"改进的"选择排序
public class HeapSort implements IArraySort {

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,3,6,90,121};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    @Override
    public int[] sort(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--){
            makeMaxHeap(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i > 0; i--){
            swap(nums, 0, i);
            makeMaxHeap(nums, 0, i);
        }
        return nums;
    }

    private void makeMaxHeap(int[] nums, int i, int len){
        int tmp = nums[i];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1){
            if (k + 1 < len && nums[k] < nums[k + 1]){
                k++;
            }
            if (nums[k] > tmp) {
                nums[i] = nums[k];
                i = k;
            }else {
                break;
            }
        }
        nums[i] = tmp;
    }

    public void heapify(int[] nums, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && nums[left] > nums[largest]) {
            largest = left;
        }

        if (right < len && nums[right] > nums[largest]) {
            largest = right;
        }

        if (i != largest) {
            swap(nums, i, largest);
            heapify(nums, i, len);
        }
    }


    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
