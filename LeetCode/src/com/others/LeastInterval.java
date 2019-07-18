package com.others;

/**
 * @author youngxinler  2019/7/18 14:25
 **/
public class LeastInterval {
    public int leastInterval(char[] tasks, int n) {
        if (tasks.length == 0) return 0;
        int ch[] = new int[256];
        int max = Integer.MIN_VALUE;
        for (char c :
                tasks) {
            ch[c]++;
        }
        for (int nums :
                ch) {
            max = Math.max(max, nums);
        }
        int count = 0;
        for (int nums :
                ch) {
            if (nums == max) count++;
        }
        return Math.max((n + 1) * (max - 1) + count, tasks.length);
    }
}
