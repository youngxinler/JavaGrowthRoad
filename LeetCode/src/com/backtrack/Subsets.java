package com.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngxinler  19-6-16 上午10:17
 * @version 0.1
 **/

public class Subsets {

    //循环枚举
    public List<List<Integer>> subsets_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        for (int num :
                nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> cur = new ArrayList<>(res.get(i));
                cur.add(num);
                res.add(cur);
            }
        }
        return res;
    }

    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        backTrack(nums, res, 0);
        return res;
    }

    private void backTrack(int[] nums, List<List<Integer>> res, int i){
        if (i >= nums.length){
            return;
        }
        int size = res.size();
        for (int j = 0; j < size; j++) {
            List<Integer> cur = new ArrayList<>(res.get(j));
            cur.add(nums[i]);
            res.add(cur);
        }
        backTrack(nums, res, i + 1);
    }
}
