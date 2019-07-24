package com.arrayAndStrings;

import java.util.Arrays;

/**
 * @author youngxinler  2019/7/23
 **/
public class LengthOfLongestSubstring {
    //滑动窗口
    public int lengthOfLongestSubstring(String s){
        int[] map = new int[128];
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            j = Math.max(map[s.charAt(i)], j);
            max = Math.max(max, i - j + 1);
            map[s.charAt(i)] = i + 1;
        }
        return max;
    }
}
