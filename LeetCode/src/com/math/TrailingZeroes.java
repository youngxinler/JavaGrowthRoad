package com.math;

/**
 * @author youngxinler  2019/7/2 10:07
 **/

/*
    阶乘后的零
    其实这道题的本质就是看阶乘中5的"倍数"的数量, 有多少个5的倍数就有多少个0
    因为阶乘中2的倍数远远多于5的倍数, 8,4,2等等都是2的倍数  (2 * 5 = 10)
 */
public class TrailingZeroes {
    public int trailingZeroes_1(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes_1(n / 5);
    }

    public int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
