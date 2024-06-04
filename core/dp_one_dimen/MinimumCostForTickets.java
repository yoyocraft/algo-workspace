package core.dp_one_dimen;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/minimum-cost-for-tickets/
 */
public class MinimumCostForTickets {

    public static final int INF = Integer.MAX_VALUE;
    public static final int[] DURATIONS = { 1, 7, 30 };
    public static final int MAXN = 366;
    public static final int[] dp = new int[MAXN];

    public int mincostTickets(int[] days, int[] costs) {
        return mincostTickets3(days, costs);
    }

    public static int mincostTickets1(int[] days, int[] costs) {
        return f1(days, costs, 0);
    }

    /* 返回从第 i 天开始的最低花费 */
    public static int f1(int[] days, int[] costs, int i) {
        if (i == days.length) {
            return 0;
        }

        int ans = INF;
        for (int k = 0, j = i; k < 3; k++) {
            // 枚举三种方案：1天、7天、30天
            while (j < days.length && days[i] + DURATIONS[k] > days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + f1(days, costs, j));
        }
        return ans;
    }

    public static int mincostTickets2(int[] days, int[] costs) {
        int[] memo = new int[days.length];
        Arrays.fill(memo, INF);
        return f2(days, costs, 0, memo);
    }

    public static int f2(int[] days, int[] costs, int i, int[] memo) {
        if (i == days.length) {
            return 0;
        }

        if (memo[i] != INF) {
            return memo[i];
        }
        int ans = INF;
        for (int k = 0, j = i; k < 3; k++) {
            while (j < days.length && days[i] + DURATIONS[k] > days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + f2(days, costs, j, memo));
        }
        return memo[i] = ans;
    }

    public static int mincostTickets3(int[] days, int[] costs) {
        int n = days.length;
        Arrays.fill(dp, 0, n + 1, INF);
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0, j = i; k < 3; k++) {
                while (j < n && days[i] + DURATIONS[k] > days[j]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], dp[j] + costs[k]);
            }
        }
        return dp[0];
    }

}
