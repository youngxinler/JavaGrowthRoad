package com.arrayAndStrings;

import java.util.Arrays;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        if (index < nums.length) {
            Arrays.fill(nums, index, nums.length, 0);
        }
    }
}
