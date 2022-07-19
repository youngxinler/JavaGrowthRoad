package com.sortAndSearch;

import java.util.Arrays;

public class InsertSort implements ArraySort {
    public static void main(String[] args) {
        int[] nums = new int[]{2,31,9,0,2,9};
        new InsertSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    @Override
    public void sort(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            int j = i;
            int cur = nums[i];
            while (j - 1 >= 0){
                if (cur < nums[j - 1]){
                    nums[j] = nums[j - 1];
                    j--;
                }else {
                    break;
                }
            }
            nums[j] = cur;
        }
    }
}
