package com.arrayAndStrings;

/**
 * @author youngxinler  19-6-1 下午5:10
 * @version 0.1
 **/

//https://leetcode-cn.com/problems/increasing-triplet-subsequence/
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int min = Integer.MAX_VALUE, max = Integer.MAX_VALUE;
        for (int num :
                nums) {
            if (num > max) {
                return true;
            } else if (num < min) {
                min = num;
            } else if (num > min && num < max) {
                max = num;
            }
        }
        return false;
    }
}
