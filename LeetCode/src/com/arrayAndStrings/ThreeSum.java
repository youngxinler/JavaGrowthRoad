package com.arrayAndStrings;

import java.util.*;


/**
 * @author youngxinler  2019/7/19
 **/
public class ThreeSum {
    //超时
    public List<List<Integer>> threeSum_1(int[] nums){
        Set<List<Integer>> ans = new HashSet<>();
        if (nums.length <= 2) return new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int a = nums[i];
                    int b = nums[j];
                    int c = nums[k];
                    if (a + b + c == 0) {
                        ans.add(threeSort(a, b, c));
                    }
                }
            }
        }
        return new ArrayList<>(ans);
    }

    private List<Integer> threeSort(int a, int b, int c){
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        return Arrays.asList(nums[0], nums[1], nums[2]);
    }

    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length <= 2) return ans;
        Arrays.sort(nums);
        int len = nums.length;
        if (nums[0] <= 0 && nums[len - 1] >= 0){
            for (int i = 0; i < len - 2;) {
                if (nums[i] > 0)break;
                int l = i + 1;
                int r = len - 1;
                do {
                    int sum = nums[i] + nums[l] + nums[r];
                    if (sum == 0){
                        ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    }
                    if (sum < 0){
                        while (nums[l] == nums[++l] && l < r);
                    }else {
                        while (nums[r] == nums[--r] && l < r);
                    }
                }while (l < r);
                while (nums[i] == nums[++i] && i < len - 2);
            }
        }
        return ans;
    }























}
