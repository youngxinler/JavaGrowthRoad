package com.sortAndSearch;

/**
 * @author youngxinler  2019/6/20 10:12
 **/
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int left = 0, right = nums.length - 1;
        int mid = -1;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[mid] != target) return res;
        res[0] = mid;
        res[1] = mid;
        while (res[0] - 1 >= 0 && nums[res[0] - 1] == target) {
            res[0]--;
        }
        while (res[1] + 1 <= nums.length - 1 && nums[res[1] + 1] == target) {
            res[1]++;
        }
        return res;
    }
}
