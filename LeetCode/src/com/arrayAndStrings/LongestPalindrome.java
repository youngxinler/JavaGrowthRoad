package com.arrayAndStrings;

/**
 * @author youngxinler  19-6-1 上午11:14
 **/

public class LongestPalindrome {
    public String longestPalindrome(String s){
        if (s == null || s.length() < 1) return "";
        int st = 0, end = 0, len = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            if (len < Math.max(len1, len2)){
                len = Math.max(len1, len2);
                st = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(st, end + 1);
    }

    private int expand(String s, int l, int r){
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return r - l - 1;
    }
}
