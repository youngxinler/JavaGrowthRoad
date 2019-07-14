package com.others;

import java.util.Stack;

/**
 * @author youngxinler  19-7-14 下午4:33
 **/

public class EvalRPN {
    public static void main(String[] args) {
        String[] strs = {"4", "13", "5", "/", "+"};
        System.out.println(new EvalRPN().evalRPN(strs));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<>();
        for (String str :
                tokens) {
            if (isNum(str)) {
                nums.push(Integer.valueOf(str));
            } else {
                int a = nums.pop();
                int b = nums.pop();
                int c = getC(str.charAt(0), b, a);
                nums.push(c);
            }
        }
        return nums.pop();
    }

    private int getC(char exp, int a, int b) {
        if (exp == '+') return a + b;
        else if (exp == '-') return a - b;
        else if (exp == '*') return a * b;
        else return a / b;
    }

    private boolean isNum(String str) {
        return !(!Character.isDigit(str.charAt(0)) && str.length() == 1);
    }
}
