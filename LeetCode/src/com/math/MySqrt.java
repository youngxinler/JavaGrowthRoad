package com.math;

/**
 * @author youngxinler  19-7-4 下午2:41
 **/

public class MySqrt {
    public int mySqrt(int x) {
        // 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
        // 为了照顾到 0 把左边界设置为 0
        long l = 0;
        // # 为了照顾到 1 把右边界设置为 x // 2 + 1
        long r = x / 2 + 1;
        while (l < r) {
            // 注意：这里一定取右中位数，如果取左中位数，代码可能会进入死循环
            long mid = l + (r - l + 1) / 2;
            long square = mid * mid;
            if (square > x) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return (int) l;
    }
}
