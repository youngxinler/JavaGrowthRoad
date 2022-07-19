package com.dp;

/**
 * @author youngxinler
 * @description TODO
 * @date 2022-06-29  22:42
 */
public class LongestPalindromicSubString {
    public static void main(String[] args) {
        String res = core("aaaa");
        System.out.println(res);
    }

    public static String core(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int left = 0;
        int max = 1;
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                int j =  i  + l - 1;
                if (j >= n) {
                    break;
                }

                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    left = i;
                }
            }
        }
        return s.substring(left, left + max);
    }
}
