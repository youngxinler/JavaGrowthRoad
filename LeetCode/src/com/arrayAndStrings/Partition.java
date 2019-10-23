package com.arrayAndStrings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Partition {

    public List<List<String>> partition(String s) {
        Set<List<String>> ans = new HashSet<>();
        if (s == null || s.length() == 0) return new ArrayList<>();
        List<String> last = new ArrayList<>();
        for (char c : s.toCharArray()) {
            last.add(String.valueOf(c));
        }
        ans.add(last);
        addItem(ans, last);
        return new ArrayList<>(ans);
    }

    private void addItem(Set<List<String>> ans, List<String> last) {
        for (int i = 0; i < last.size() - 1; i++) {
            String cur = last.get(i) + last.get(i + 1);
            if (isCircle(cur)) {
                List<String> curItem = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    curItem.add(last.get(j));
                }
                curItem.add(cur);
                for (int j = i + 2; i < last.size(); i++) {
                    curItem.add(last.get(j));
                }
                ans.add(curItem);
                addItem(ans, curItem);
            }
        }
    }

    private boolean isCircle(String s) {
        char[] cs = s.toCharArray();
        int l = 0, r = cs.length - 1;
        while (l <= r) {
            if (cs[l++] != cs[r--]) return false;
        }
        return true;
    }
}
