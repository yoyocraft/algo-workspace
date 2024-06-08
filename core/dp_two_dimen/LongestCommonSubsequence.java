package core.dp_two_dimen;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String s, String t) {
        return longestCommonSubsequence1(s, t);
    }

    public static int longestCommonSubsequence1(String ss, String ts) {
        char[] s = ss.toCharArray();
        char[] t = ts.toCharArray();
        int n = s.length;
        int m = t.length;
        return f1(s, t, n, m);
    }

    public static int f1(char[] s, char[] t, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }

        int ans = 0;
        if (s[n - 1] == t[m - 1]) {
            ans = f1(s, t, n - 1, m - 1) + 1;
        } else {
            ans = Math.max(f1(s, t, n - 1, m), f1(s, t, n, m - 1));
        }
        return ans;
    }

    public static int longestCommonSubsequence2(String ss, String ts) {
        char[] s = ss.toCharArray();
        char[] t = ts.toCharArray();
        int n = s.length;
        int m = t.length;
        int[][] memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }
        return f2(s, t, n, m, memo);
    }

    public static int f2(char[] s, char[] t, int n, int m, int[][] memo) {
        if (n == 0 || m == 0) {
            return 0;
        }

        if (memo[n - 1][m - 1] != -1) {
            return memo[n - 1][m - 1];
        }

        int ans = 0;
        if (s[n - 1] == t[m - 1]) {
            ans = f2(s, t, n - 1, m - 1, memo) + 1;
        } else {
            ans = Math.max(f2(s, t, n - 1, m, memo), f2(s, t, n, m - 1, memo));
        }
        return memo[n - 1][m - 1] = ans;
    }

    public static int longestCommonSubsequence3(String ss, String ts) {
        char[] s = ss.toCharArray();
        char[] t = ts.toCharArray();
        int n = s.length, m = t.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s[i - 1] == t[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public static int longestCommonSubsequence4(String ss, String ts) {
        char[] s, t;
        if (ss.length() >= ts.length()) {
            s = ss.toCharArray();
            t = ts.toCharArray();
        } else {
            s = ts.toCharArray();
            t = ss.toCharArray();
        }

        int n = s.length, m = t.length;
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            int leftUp = 0, backup;
            for (int j = 1; j <= m; j++) {
                backup = dp[j]; // (i,j) 作为后续计算的左上角元素
                if (s[i - 1] == t[j - 1]) {
                    dp[j] = 1 + leftUp;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                leftUp = backup;
            }
        }
        return dp[m];
    }

}