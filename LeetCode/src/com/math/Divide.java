package com.math;

/**
 * @author youngxinler  19-7-11 上午10:13
 **/

public class Divide {
    public int divide(int divided, int divisor) {
        int sign = (divided ^ divisor) >> 31;
        long lDivided = Math.abs((long) divided);
        long lDivisor = Math.abs((long) divisor);
        long ans = 0;
        while (lDivided >= lDivisor) {
            long tmp = lDivided;
            long i = 1;
            while (lDivided >= tmp) {
                lDivided -= tmp;
                ans += i;
                i <<= 1;
                tmp <<= 1;
            }
        }
        if (sign == -1) ans = -ans;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        else return (int) ans;
    }
}
