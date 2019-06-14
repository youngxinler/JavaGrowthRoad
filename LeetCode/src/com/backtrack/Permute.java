package com.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngxinler  2019/6/13 10:58
 **/
public class Permute {


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, new ArrayList<>(), nums);
        return res;
    }

    //回溯解决! 注意字段引用的对象!!!
    private void backTrack(List<List<Integer>> res, List<Integer> cur, int[] nums) {
        if (cur.size() == nums.length) {
            res.add(cur);
            return;
        }
        List<Integer> now;
        for (int i = 0; i < nums.length; i++) {
            if (!cur.contains(nums[i])) {
                now = new ArrayList<>(cur);
                now.add(nums[i]);
                backTrack(res, now, nums);
            }
        }
    }
}
