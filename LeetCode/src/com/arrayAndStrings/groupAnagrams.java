package com.arrayAndStrings;

import java.util.*;

/**
 * @author youngxinler  19-6-1 下午7:00
 * @version 0.1
 **/

public class groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        final Map<String, List<String>> map = new HashMap<>();
        final List<List<String>> res = new ArrayList<>();
        for (String str :
                strs) {
            String sortedString = sort(str);
            if (map.containsKey(sortedString)) {
                map.get(sortedString).add(str);
            } else {
                List<String> rows = new ArrayList<>();
                rows.add(str);
                map.put(sortedString, rows);
            }
        }
        for (Map.Entry<String, List<String>> entry :
                map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    private String sort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
