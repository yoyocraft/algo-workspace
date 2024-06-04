package core.dp_one_dimen;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/decode-ways/
 */
public class DecodeWays {

    public int numDecodings(String s) {
        return numDecodings2(s);
    }

    public static int numDecodings1(String s) {
        return f1(s.toCharArray(), 0);
    }

    public static int f1(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }

        int ans;
        if (s[i] == '0') {
            return 0;
        } else {
            ans = f1(s, i + 1);
            if (i + 1 < s.length && (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7'))) {
                ans += f1(s, i + 2);
            }
        }
        return ans;
    }

    public static int numDecodings2(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return f2(s.toCharArray(), 0, memo);
    }

    public static int f2(char[] s, int i, int[] memo) {
        if (i == s.length) {
            return 1;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int ans;
        if (s[i] == '0') {
            return 0;
        } else {
            ans = f2(s, i + 1, memo);
            if (i + 1 < s.length && (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7'))) {
                ans += f2(s, i + 2, memo);
            }
        }
        return memo[i] = ans;
    }

    public static int numDecodings3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i + 1 < s.length && (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7'))) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    public static int numDecodings4(String str) {
        int dp1 = 1, dp2 = 0;
        char[] s = str.toCharArray();
        for (int i = s.length - 1, dp0; i >= 0; i--) {
            if (s[i] == '0') {
                dp0 = 0;
            } else {
                dp0 = dp1;
                if (i + 1 < s.length && (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7'))) {
                    dp0 += dp2;
                }
            }
            dp2 = dp1;
            dp1 = dp0;
        }
        return dp1;
    }
}
