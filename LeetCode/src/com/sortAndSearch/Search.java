package com.sortAndSearch;

/**
 * @author youngxinler  19-6-22 上午9:51
 * @version 0.1
 **/

public class Search {
    private int[] nums;
    private int target;

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        this.nums = nums;
        this.target = target;
        int rotateIndex = findRotateIndex(0, nums.length - 1);
        if (rotateIndex == 0) {
            return search(0, nums.length - 1);
        } else if (target == nums[0]) {
            return 0;
        } else if (target > nums[0]) {
            return search(1, rotateIndex - 1);
        } else {
            return search(rotateIndex, nums.length - 1);
        }
    }

    private int search(int l, int r) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    private int findRotateIndex(int l, int r) {
        if (nums[l] < nums[r]) {
            return 0;
        }
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return mid + 1;
            } else {
                if (nums[mid] >= nums[l]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return 0;
    }
}
