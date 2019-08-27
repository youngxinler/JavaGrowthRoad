package com.sortAndSearch;

/**
 * @author youngxinler  2019/6/20 10:12
 **/
public class SearchRange {
    public int[] searchRange(int[] nums, int target){
        if (nums == null || nums.length == 0)
            return new int[]{-1,-1};
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right){
            mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                break;
            }
        }
        if (nums[mid] != target)
            return new int[]{-1, -1};
        left = mid;
        right = mid;
        while (left - 1 >= 0 && nums[left - 1] == target){
            left--;
        };
        while (right + 1 < nums.length && nums[right + 1] == target){
            right++;
        };
        return new int[]{left, right};
    }
}
