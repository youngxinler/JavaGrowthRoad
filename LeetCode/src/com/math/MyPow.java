package com.math;

/**
 * @author youngxinler  19-7-10 下午7:24
 **/

public class MyPow {

    //暴力法
    public double myPow_1(double x, int n) {
        double ans = 1;
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        for (int i = 0; i < N; i++) {
            ans *= x;
        }
        return ans;
    }

    //快速幂 -- 递归
    private double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPow_2(double x, int n) {
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    //快速幂 -- 循环
    public double myPow(double x, int n) {
        //因为int类型-2147483648~2147483647, 所以如果拿-2147483648, 会超出int正数范围.
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 == 1) {
                ans *= current_product;
            }
            current_product *= current_product;
        }
        return ans;
    }
}
