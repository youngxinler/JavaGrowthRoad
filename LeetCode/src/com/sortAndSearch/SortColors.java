package com.sortAndSearch;

/**
 * @author youngxinler  19-6-6 下午5:25
 * @version 0.1
 **/

public class SortColors {

    //类似计数排序
    public void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;

        for (int num :
                nums) {
            if (num == 0) {
                zeros++;
            } else if (num == 1) {
                ones++;
            }
        }

        for (int i = 0; i < zeros; i++) {
            nums[i] = 0;
        }

        for (int i = zeros; i < ones + zeros; i++) {
            nums[i] = 1;
        }

        for (int i = zeros + ones; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}
