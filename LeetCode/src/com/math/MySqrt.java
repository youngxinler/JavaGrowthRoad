package com.math;

/**
 * @author youngxinler  19-7-4 下午2:41
 **/

public class MySqrt {
    public int mySqrt(int x) {
        long l = 0;
        long r = x / 2 + 1;
        while (l < r) {
            long mid = l + (r - l + 1) / 2;
            long s = mid * mid;
            if (s > x) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return (int) l;
    }
}
