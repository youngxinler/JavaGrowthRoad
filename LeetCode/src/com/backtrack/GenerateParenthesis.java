package com.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngxinler  19-6-12 下午4:59
 * @version 0.1
 **/

public class GenerateParenthesis {


    public List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        backTrace(res, "", 0, 0, n);
        return res;
    }

    private void backTrace(List<String> res, String cur, int open, int close, int n){
        int len = cur.length();
        if (len == n * 2){
            res.add(cur);
            return;
        }
        if (open < n){
            backTrace(res, cur + "(", open + 1, close, n);
        }
        if (close < open){
            backTrace(res, cur + ")", open, close + 1, n);
        }
    }
}
