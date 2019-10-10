package com.sortAndSearch;

//计数排序只适合, 数组中的数值分布在一定区间内.
public class CountingSort implements IArraySort {
    @Override
    public int[] sort(int[] nums) {
        //求出数组中的最大值
        int maxValue = getMaxValue(nums);
        return countingSort(nums, maxValue);
    }

    private int[] countingSort(int[] nums, int maxValue) {
        int bucketLen = maxValue + 1;
        //bucket[i]的值代表的是有bucket[i]个值为i的数.
        int[] bucket = new int[bucketLen];

        //统计数的"分布"
        for (int num : nums) {
            bucket[num]++;
        }

        int index = 0;
        //从值为0开始统计. (这里可能存在一个疑问, 如果从0开始, 那么数组中存在小于0的数是否还可以用计数排序?
        // 当然可以, 可以找出最小值, 设置一个钩子值, 让所有数都加上值, 使最小值映射到0上. 最后所有数再减去这个钩子值.
        for (int i = 0; i < bucketLen; i++) {
            while (bucket[i] > 0) {
                nums[index++] = i;
                bucket[i]--;
            }
        }
        return nums;
    }

    private int getMaxValue(int[] nums) {
        int maxValue = nums[0];
        for (int num : nums) {
            maxValue = Math.max(num, maxValue);
        }
        return maxValue;
    }
}
