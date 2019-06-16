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

    //递归枚举
    public List<List<Integer>> subsets_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        recursion(nums, 0, res);
        return res;
    }

    private void recursion(int[] nums, int i, List<List<Integer>> res) {
        if (i >= nums.length) {
            return;
        }
        int size = res.size();
        for (int j = 0; j < size; j++) {
            List<Integer> cur = new ArrayList<>(res.get(j));
            cur.add(nums[i]);
            res.add(cur);
        }
        recursion(nums, i + 1, res);
    }
}
