package com.sortAndSearch;

//堆排序是一种"改进的"选择排序
public class HeapSort implements IArraySort {
    @Override
    public int[] sort(int[] nums) {
        int len = nums.length;
        //构成全体最大堆
        buildMaxHeap(nums, len);
        for (int i = len - 1; i > 0; i--) {
            swap(nums, 0, i);
            len--;
            toMaxHeap(nums, i, len);
        }
        return nums;
    }

    private void buildMaxHeap(int[] nums, int len) {
        //将整个数组构成最大堆
        for (int i = len / 2; i >= 0; i--) {
            toMaxHeap(nums, i, len);
        }
    }

    private void toMaxHeap(int[] nums, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        //将由一个父节点和两个子节点(如果存在)的数进行排序.
        if (left < len && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < len && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(nums, i, largest);
            //这里i != largest, 以largest为下标的元素已经不是原来那三个数,
            // 所以这里不是冗余的操作, 而是交换之后, 原来以nums[largest]为父节点的子树(最大堆)可能会遭到破坏
            // 这里重新组建最大堆
            toMaxHeap(nums, largest, len);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
