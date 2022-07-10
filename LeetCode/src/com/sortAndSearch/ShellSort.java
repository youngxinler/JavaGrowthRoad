package com.sortAndSearch;

import java.util.Arrays;

public class ShellSort implements ArraySort {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,0,9,9,76};
        new ShellSort().sort(nums);
        System.out.println(Arrays.toString(nums));
   }
    @Override
    public void sort(int[] nums) {
        for (int gap = nums.length / 2; gap >= 1; gap = gap / 2){
            for (int i = gap; i < nums.length; i++){
                int temp = nums[i];
                int j = i;
                while (j - gap >= 0 && temp < nums[j - gap]){
                    nums[j] = nums[j - gap];
                    j -= gap;
                }
                nums[j] = temp;
            }
        }
    }
}
