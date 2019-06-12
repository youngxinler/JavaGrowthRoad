package com.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngxinler  19-6-12 下午4:59
 * @version 0.1
 **/

public class GenerateParenthesis {


    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTrack(res, "", 0, 0, n);
        return res;
    }

    //回溯算法
    private void backTrack(List<String> ans, String cur, int open, int close, int n) {
        if (cur.length() == n * 2) {
            ans.add(cur);
            return;
        }

        if (open < n) {
            backTrack(ans, cur + "(", open + 1, close, n);
        }
        if (close < open) {
            backTrack(ans, cur + ")", open, close + 1, n);
        }
    }
}
