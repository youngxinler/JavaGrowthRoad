package com.sortAndSearch;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2};
        Main main = new Main();
        System.out.println(main.trap(nums));;
        System.out.println(Arrays.toString(nums));
    }

    public int[] singleNumbers(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums){
            if (set.contains(num)){
                set.remove(num);
            }else {
                set.add(num);
            }
        }
        int[] res = new int[set.size()];
        Integer[] c = (Integer[]) set.toArray();
        for (int i = 0; i < res.length; i++){
            res[i] = c[i];
        }
        return res;
    }
    public int trap(int[] height) {
        if(height == null || height.length < 3){
            return 0;
        }
        int len = height.length;
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        maxLeft[0] = height[0];
        maxRight[len - 1] = height[len - 1];
        for(int i = 1; i < len - 1; i++){
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }
        for(int i = len - 2; i >= 0; i--){
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }
        int sum = 0;
        for(int i = 1; i <= len - 2; i++){
            int minHeight = Math.min(maxLeft[i], maxRight[i]);
            if(minHeight > height[i]){
                sum += (minHeight - height[i]);
            }
        }
        return sum;
    }
}
