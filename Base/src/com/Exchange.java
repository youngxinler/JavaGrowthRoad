package com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youngxinler
 * @description TODO
 * @date 2020-08-27  11:45
 */
public class Exchange {
    public void mergeSort(int[] nums){
        int[] tmp = new int[nums.length];
        sort(nums, 0, nums.length - 1, tmp);
    }

    private void sort(int[] nums, int left, int right, int[] tmp){
        if (left < right){
            int mid = (left + right) / 2;
            sort(nums, left, mid, tmp);
            sort(nums, mid + 1, right, tmp);
            merge(nums, left, mid, right, tmp);
        }
    }

    private void merge(int[] nums, int left, int mid, int right, int[] tmp){
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= left && j <= right){
            if (nums[i] <= nums[j]){
                tmp[k++] = nums[i++];
            }else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid){
            tmp[k++] = nums[i++];
        }
        while (j <= right){
            tmp[k++] = nums[j++];
        }
        k = 0;
        while (left <= right){
            nums[left++] = tmp[k++];
        }
    }

    public static void main(String[] args) {
        Exchange ex = new Exchange();
        int[] nums = new int[]{9,82,2819,2};
        ex.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
