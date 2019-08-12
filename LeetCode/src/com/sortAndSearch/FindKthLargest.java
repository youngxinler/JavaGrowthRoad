package com.sortAndSearch;

import java.util.PriorityQueue;

/**
 * @author youngxinler  2019/6/18 9:14
 **/
public class FindKthLargest {
    //冒泡
    public int findKthLargest_1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
            if (i == k - 1) {
                return nums[i];
            }
        }
        return -1;
    }

    //插入
    public int findKthLargest_2(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (nums[i] > nums[j]) break;
            }
            int temp = nums[i];
            System.arraycopy(nums, j, nums, j + 1, i - j);
            nums[j] = temp;
        }
        return nums[k - 1];
    }

    //最小堆
    public int findKthLargest_3(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() < k){
                queue.add(nums[i]);
                continue;
            }
            if (queue.peek() < nums[i]){
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }

    //巧用快排
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = partition(nums, left, right);
            if (mid == k - 1) return nums[k - 1];
            else if (mid > k - 1) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }


    private int partition(int[] nums, int left, int right) {
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (nums[i] > nums[left]) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, left, index - 1);
        return index - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
