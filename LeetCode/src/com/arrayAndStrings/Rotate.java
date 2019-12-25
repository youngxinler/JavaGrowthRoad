package com.arrayAndStrings;

public class Rotate {
    public void rotate_1(int[] nums, int k) {
        int[] a = new int[nums.length];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            a[(i + k) % len] = nums[i];
        }
        System.arraycopy(a, 0, nums, 0, len);
    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
