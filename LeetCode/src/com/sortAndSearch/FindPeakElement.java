package com.sortAndSearch;

/**
 * @author youngxinler  19-6-19 下午2:13
 * @version 0.1
 **/

public class FindPeakElement {

    //顺序遍历
    public int findPeakElement_1(int[] nums) {
        int len = nums.length;
        if (len < 2) return 0;
        if (len == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        if (nums[len - 1] > nums[len - 2]) return len - 1;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1]) {
                if (nums[i] > nums[i + 1]) {
                    return i;
                }
            }
        }
        return 0;
    }

    //二分查找
    //num[i] != num[i + 1] 该题使用二分的关键
    //num[i] != num[i + 1] 使得3个位一组.
    public int findPeakElement(int[] nums){
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]){
                //right指向较大
                right = mid;
            }else {
                //更新left, 使指向最终的答案
                left = mid + 1;
            }
        }
        return left;
    }
}
