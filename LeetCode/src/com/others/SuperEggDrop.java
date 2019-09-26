package com.others;

import java.util.HashMap;
import java.util.Map;

public class SuperEggDrop {
    public int superEggDrop_1(int K, int N) {
        int[][] middleResults = new int[K + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            middleResults[1][i] = i;
            middleResults[0][i] = 0;
        }
        for (int i = 1; i <= K; i++) {
            middleResults[i][0] = 0;
        }

        for (int k = 2; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                int rMin = N * N;
                for (int x = 1; x <= n; x++) {
                    rMin = Math.min(rMin, 1 + Math.max(middleResults[k - 1][x - 1], middleResults[k][n - x]));
                }
                middleResults[k][n] = rMin;
            }
        }
        return middleResults[K][N];
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int superEggDrop_2(int K, int N) {
        if (N == 0) {
            return 0;
        } else if (K == 1) {
            return N;
        }

        int key = N * 1000 + K; // k <= 100 这里通过k占低位， 来构造一个key。
        if (map.containsValue(key)) {
            return map.get(key);
        }

        int low = 1, high = N;
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            int lowVal = superEggDrop_2(K - 1, mid - 1);
            int highVal = superEggDrop_2(K, N - mid);

            if (lowVal < highVal) {
                low = mid;
            } else if (lowVal > highVal) {
                high = mid;
            } else {
                low = high = mid;
            }
        }
        int miniNum = 1 + Math.min(
                Math.max(superEggDrop_2(K - 1, low - 1), superEggDrop_2(K, N - low)),
                Math.max(superEggDrop_2(K - 1, high - 1), superEggDrop_2(K, N - high))
        );
        map.put(key, miniNum);
        return miniNum;
    }

    public int superEggDrop(int K, int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = i;
        }
        for (int k = 2; k <= K; k++) {
            int[] dp2 = new int[N + 1];
            int x = 1;
            for (int n = 1; n <= N; n++) {
                while (x < n && Math.max(dp[x - 1], dp2[n - x]) > Math.max(dp[x], dp2[n - x - 1])) {
                    x++;
                }

                dp2[n] = 1 + Math.max(dp[x - 1], dp2[n - x]);
            }
            dp = dp2;
        }
        return dp[N];
    }

    private int recursive(int k, int n) {
        if (n == 0 || k == 1 || n == 1) {
            return n;
        }

        int miniNum = n;

        for (int i = 1; i <= n; i++) {
            //分为“两座楼”， 分别进行递归
            int rMin = Math.max(recursive(k - 1, i - 1), recursive(k, n - i));
            miniNum = Math.min(miniNum, 1 + rMin);
        }
        return miniNum;
    }
}
