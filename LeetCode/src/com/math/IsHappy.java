package com.math;

/**
 * @author youngxinler  2019/7/1 10:30
 **/
//快乐数
public class IsHappy {
    public boolean isHappy(int n) {
        if (n == 1 || n == 7) return true;
        if (n < 10) return false;

        int sum = 0;
        while (n != 0) {
            int last = n % 10;
            sum += last * last;
            n /= 10;
        }
        return isHappy(sum);
    }
}
