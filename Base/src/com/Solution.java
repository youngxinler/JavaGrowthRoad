package com;
import java.util.*;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        Solution sl = new Solution();
        String[] strs = new String[] {"4","13","5","/","+"};
        int ret = sl.evalRPN(strs);
        System.out.println(ret);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String cur = tokens[i];
            if (isNumber(cur)) {
                stack.push(Integer.valueOf(cur));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                char op = cur.charAt(0);
                if (op == '+') {
                    stack.push(a + b);
                } else if (op == '-') {
                    stack.push(b - a);
                } else if (op == '*') {
                    stack.push(a * b);
                } else if (op == '/') {
                    stack.push(b / a);
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String str) {
        if (str.charAt(0) == '-' || (str.charAt(0) == '+' && str.length() >= 2)) {
            for (int i = 1; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}