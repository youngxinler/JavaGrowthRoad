package com.dynamicProgram;

/**
 * @author youngxinler  19-6-26 下午3:51
 * @version 0.1
 **/

//零钱兑换
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 0; i < amount; i++) {
            int cost = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    cost = Math.min(cost, dp[i - coins[j]] + 1);
                }
            }
        }


        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
