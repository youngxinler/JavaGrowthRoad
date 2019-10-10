package com.sortAndSearch;

import java.util.Arrays;

public class RadixSort implements IArraySort {
    @Override
    public int[] sort(int[] nums) {
        int maxLen = getMaxLength(getMaxValue(nums));
        return radixSort(nums, maxLen);
    }

    //得出最大数的位数
    private int getMaxLength(int maxValue) {
        int len = 0;
        if (maxValue == 0) {
            return 1;
        }
        for (int i = maxValue; i > 0; i /= 10) {
            len++;
        }
        return len;
    }

    private int getMaxValue(int[] nums) {
        int maxValue = nums[0];
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }
        return maxValue;
    }

    private int[] radixSort(int[] nums, int maxDigit) {
        int mod = 10;
        int dev = 1;
        for (int i = 0; i < maxDigit; i++, mod *= 10, dev *= 10) {
            //mod * 2   0-9负数 10-19正数
            int[][] counter = new int[mod * 2][0];

            for (int num : nums) {
                //index为比较位的值
                int index = num % mod / dev + mod;
                //跟据index即可确定该比较位的大小, 从而装入不同的桶中.
                counter[index] = arrayAppend(counter[index], num);
            }
            int index = 0;
            //counter这个数组桶是排好顺序的.
            for (int[] e : counter) {
                for (int num : e) {
                    //按比较位的大小填入nums, 每一位的比较, 都在向最终结果迈进
                    nums[index++] = num;
                }
            }
        }
        return nums;
    }

    private int[] arrayAppend(int[] nums, int value) {
        nums = Arrays.copyOf(nums, nums.length + 1);
        nums[nums.length - 1] = value;
        return nums;
    }
}
