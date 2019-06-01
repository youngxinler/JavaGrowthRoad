package com.arrayAndStrings;

/**
 * @author youngxinler  19-6-1 上午11:14
 * @version 0.1
 **/

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int lenJ = expandAroundCenter(s, i, i);
            int lenO = expandAroundCenter(s, i, i + 1);
            int len = Math.max(lenJ, lenO);
            if (len > start - end){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R - L - 1;
    }
}
