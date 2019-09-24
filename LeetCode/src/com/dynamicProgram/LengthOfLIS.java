package com.dynamicProgram;

import java.util.Arrays;

/**
 * @author youngxinler  2019/6/27 22:22
 **/


//Longest Increasing Subsequence
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 4};
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        int ans = lengthOfLIS.lengthOfLIS(nums);
        System.out.println(ans);
    }

    //[10,9,2,5,3,7,101,18]
    public int lengthOfLIS_1(int[] nums) {
        if (nums.length == 0) return 0;
        int dp[] = new int[nums.length + 1];
        Arrays.fill(dp, 1);
        int allMax = 1;
        for (int i = 0; i < nums.length; i++) {
            int max = 1;
            int j = i - 1;
            while (j >= 0) {
                if (nums[i] > nums[j]) {
                    max = dp[j] + 1 > max ? dp[j] + 1 : max;
                }
                j--;
            }
            dp[i] = max;
            allMax = allMax > max ? allMax : max;
        }
        return allMax;
    }

    public int lengthOfLIS_2(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        // 这个数组实际上的长度，就是最后所求
        int[] tail = new int[len];
        int end = 0;
        tail[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > tail[end]) {
                end++;
                tail[end] = nums[i];
            } else {
                // 使用二分搜索法来做这件事情
                // 数组长度不变，一定会更新一次
                // 特殊例子：1 2 3 4 5 7 7 7 7 7 7 7 （只是举个例子，这道题不会出现这种情况），来了一个 6
                // 我要将从左到右边数第 1 个大于 6 的数字更新为 6
                int left = 0;
                int right = end;
                int target = nums[i];
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (tail[mid] < target) {
                        // 只要比目标值要小，我们要找的位置就至少是当前位置 + 1
                        left = mid + 1;
                    } else {
                        assert tail[mid] >= target;
                        // 大于目标值，我们不能盲目向前走，因为向前走很可能，值会变得比目标值小
                        right = mid;
                    }
                }
                tail[right] = target;
            }
        }
        return end + 1;
    }


    //[10,9,2,5,3,4]
    public int lengthOfLIS_3(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] tail = new int[len];
        int end = 0;
        tail[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tail[end]) {
                end++;
                tail[end] = nums[i];
            } else {
                int right = end;
                int left = 0;
                int target = nums[i];
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (tail[mid] < target) {
                        left = mid + 1;
                    } else {
                        assert tail[mid] >= target;
                        right = mid;
                    }
                }
                tail[right] = target;
            }
        }
        return end + 1;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int allMax = 1;
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            int j = i - 1;
            int max = 1;
            while (j >= 0) {
                if (nums[i] > nums[j]) {
                    max = dp[j] + 1 > max ? dp[j] + 1 : max;
                }
                j--;
            }
            dp[i] = max;
            allMax = allMax > max ? allMax : max;
        }
        return allMax;
    }
}
