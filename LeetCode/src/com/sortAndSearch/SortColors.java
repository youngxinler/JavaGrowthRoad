package com.sortAndSearch;

/**
 * @author youngxinler  19-6-6 下午5:25
 * @version 0.1
 **/

public class SortColors {
    public void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;
        /*
            记录0 和 1的个数, 最后更改数组
         */
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
        for (int i = zeros; i < ones; i++) {
            nums[i] = 1;
        }
        for (int i = zeros + ones; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}
