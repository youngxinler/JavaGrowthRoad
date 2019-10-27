package com.sortAndSearch;

import java.util.Arrays;

//桶排序有点类似计数排序, 他使用映射函数尽可能地将数组进行均匀分配到每个桶中
// (桶和桶之间的临界值是有序的!)
//然后在每个桶中再单独进行排序
public class BucketSort implements IArraySort {
    private final static InsertSort insertSort = new InsertSort();

    @Override
    public int[] sort(int[] nums) {
        return bucketSort(nums, 5);
    }

    private int[] bucketSort(int[] nums, int bucketSize) {
        if (nums.length == 0) {
            return nums;
        }

        int max = nums[0];
        int min = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            } else if (num < min) {
                min = num;
            }
        }

        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][0];
        for (int num : nums) {
            int index = (num - min) / bucketSize;
            buckets[index] = arrAppend(buckets[index], num);
        }

        int arrIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length == 0) continue;

            insertSort.sort(bucket);
            for (int num : bucket) {
                nums[arrIndex++] = num;
            }
        }
        return nums;
    }

    private int[] arrAppend(int[] bucket, int num) {
        bucket = Arrays.copyOf(bucket, bucket.length + 1);
        bucket[bucket.length - 1] = num;
        return bucket;
    }
}
