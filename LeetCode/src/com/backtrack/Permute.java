package com.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngxinler  2019/6/13 10:58
 **/
public class Permute {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, nums, new ArrayList<>(), nums.length);
        return res;
    }

    private void backTrack(List<List<Integer>> res, int[] nums, List<Integer> cur, int n){
        int len = cur.size();
        if (len == n){
            res.add(cur);
            return;
        }
        for (int num:
             nums) {
            if (cur.indexOf(num) != -1) continue;
            List<Integer> next = new ArrayList<>(cur);
            next.add(num);
            backTrack(res, nums, next, n);
        }
    }
}
