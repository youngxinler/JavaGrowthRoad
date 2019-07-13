package com.others;

/**
 * @author youngxinler  19-7-13 下午2:51
 **/

public class GetSum {
    public int getSum(int a, int b) {
        int sum = a ^ b;
        //记录进位
        int up = a & b;
        if (up != 0) {
            //up不为0存在进位, 将up进位到前一位.
            up <<= 1;
            return getSum(sum, up);
        } else {
            return sum;
        }
    }
}
