package com.dynamicProgram;

/**
 * @author youngxinler  19-6-25 下午6:40
 * @version 0.1
 **/

//不同路径
public class UniquePaths {
    public int uniquePaths_(int m, int n) {
        return floor(m + n - 2) / floor(m - 1) / floor(n - 1);
    }

    private int floor(int m) {
        int sum = 1;
        for (int i = 1; i <= m; i++) {
            sum *= m;
        }
        return sum;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n - 1][m - 1];
    }
}
