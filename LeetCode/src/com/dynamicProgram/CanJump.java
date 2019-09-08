package com.dynamicProgram;

/**
 * @author youngxinler  2019/6/24 17:11
 **/


public class CanJump {
    public boolean canJump_1(int[] nums) {
        if (nums.length == 1) return true;
        int i = 0;
        int last = 0;
        while (i < nums.length) {
            int curMax = last - 1 > nums[i] ? last - 1 : nums[i];
            if (curMax == 0) return false;
            else if (curMax >= nums.length - 1 - i) return true;
            else {
                last = curMax;
                i++;
            }
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
}
