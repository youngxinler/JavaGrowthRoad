package com.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    private final Map<String, String> map = new HashMap<>(9);
//    {{
//        put("2", "abc");
//        put("3", "def");
//        put("4", "ghi");
//        put("5", "jkl");
//        put("6", "mno");
//        put("7", "pqrs");
//        put("8", "tuv");
//        put("9", "wxyz");
//    }};

    private final List<String> res = new ArrayList<>(500);

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            backTrack("", digits);
        }
        return res;
    }

    private void backTrack(String combinations, String cur) {
        if (cur.length() != 0) {
            String now = cur.substring(0, 1);
            String letters = map.get(now);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                backTrack(combinations + letter, cur.substring(1));
            }
        } else {
            res.add(combinations);
        }
    }
}
