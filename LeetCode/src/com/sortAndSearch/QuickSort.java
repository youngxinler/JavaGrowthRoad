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

    private int[] quickSort(int[] nums, int left, int right) {
        //left >= right 排序递归终止
        if (left < right) {
            //index 为分离之后基准值的下标
            int index = partition(nums, left, right);
            //根据分离之后基准值的下标, 再将两边分别进行快排
            quickSort(nums, left, index);
            quickSort(nums, index + 1, right);
        }
        return nums;
    }

    //partition函数使以基准值为中心, 分开left < i < right的num[i], 默认使用num[left]作为基准值
    private int partition(int[] nums, int left, int right) {
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            //下标为i  (left < i < index)  为小于基准值的数
            if (nums[i] < nums[left]) {
                swap(nums, index, i);
                //index++ 使num[index] >= num[left] 即基准值
                index++;
            }
        }
        swap(nums, left, index - 1);
        return index - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
