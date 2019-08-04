package com.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {


    private final Map<String, String> map = new HashMap<String, String>(9) {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private int len;

    public List<String> letterCombinations(String digits){
        List<String> ans = new ArrayList<>();
        this.len = digits.length();
        if (digits.length() != 0){
            backTrace(ans, "", digits);
            return ans;
        }else {
            return ans;
        }
    }

    private void backTrace(List<String> res, String cm, String digits){
        if (cm.length() != len){
            String one = digits.substring(0, 1);
            String cs = map.get(one);
            for (int i = 0; i < cs.length(); i++) {
                backTrace(res, cm + cs.charAt(i), digits.substring(1));
            }
        }else {
            res.add(cm);
        }
    }
}
