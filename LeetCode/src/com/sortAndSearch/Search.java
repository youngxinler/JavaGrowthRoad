package com.sortAndSearch;

/**
 * @author youngxinler  19-6-22 上午9:51
 * @version 0.1
 **/

public class Search {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int rotateIndex = getRotateIndex(nums);
        if (rotateIndex == 0) {
            return binarySearch(nums, 0, nums.length - 1, target);
        } else if (nums[0] == target) {
            return 0;
        } else if (target > nums[0]) {
            return binarySearch(nums, 0, rotateIndex - 1, target);
        } else {
            return binarySearch(nums, rotateIndex, nums.length - 1, target);
        }
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    private int getRotateIndex(int[] nums) {
        int l = 0, r = nums.length - 1;
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
