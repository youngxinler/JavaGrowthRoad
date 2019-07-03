package com.math;

/**
 * @author youngxinler  19-7-3 下午12:25
 **/

public class TitleToNumber {
    public int titleToNumber(String s) {
        if (s.length() == 0) return 0;
        int sum = 0;
        char[] cs = s.toCharArray();
        int len = cs.length;
        for (int i = len - 1; i >= 0; i--) {
            sum += (cs[i] - 64) * Math.pow(26, len - i - 1);
        }
        return sum;
    }
}
