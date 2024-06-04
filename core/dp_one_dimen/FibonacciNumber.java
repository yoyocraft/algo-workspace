package core.dp_one_dimen;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 */
public class FibonacciNumber {
    public int fib(int n) {
        return fib3(n);
    }

    public static int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fib1(n - 1) + fib1(n - 2);
    }

    public static int fib2(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return fib2helper(n, memo);
    }

    public static int fib2helper(int i, int[] memo) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        return memo[i] = fib2helper(i - 1, memo) + fib2helper(i - 2, memo);
    }

    public static int fib3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int fib4(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int dp0 = 0, dp1 = 1;
        for (int i = 2, dp2; i <= n; i++) {
            dp2 = dp0 + dp1;
            dp0 = dp1;
            dp1 = dp2;
        }
        return dp1;
    }
}
