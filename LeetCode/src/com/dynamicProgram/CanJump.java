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

    //贪心
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
