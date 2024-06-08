package core.dp_two_dimen;

/**
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseq4(s);
    }

    public static int longestPalindromeSubseq1(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        return f1(s, 0, n - 1);
    }

    public static int f1(char[] s, int l, int r) {
        if (l == r) {
            return 1;
        }

        if (l + 1 == r) {
            return s[l] == s[r] ? 2 : 1;
        }
        if (s[l] == s[r]) {
            return 2 + f1(s, l + 1, r - 1);
        } else {
            return Math.max(f1(s, l + 1, r), f1(s, l, r - 1));
        }
    }

    public static int longestPalindromeSubseq2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] memo = new int[n][n];
        return f2(s, 0, n - 1, memo);
    }

    public static int f2(char[] s, int l, int r, int[][] memo) {
        if (l == r) {
            return 1;
        }

        if (l + 1 == r) {
            return s[l] == s[r] ? 2 : 1;
        }

        if (memo[l][r] != 0) {
            return memo[l][r];
        }

        int ans;
        if (s[l] == s[r]) {
            ans = 2 + f2(s, l + 1, r - 1, memo);
        } else {
            ans = Math.max(f2(s, l + 1, r, memo), f2(s, l, r - 1, memo));
        }
        return memo[l][r] = ans;
    }

    public static int longestPalindromeSubseq3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int l = n - 1; l >= 0; l--) {
            dp[l][l] = 1;
            if (l + 1 < n) {
                dp[l][l + 1] = s[l] == s[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                if (s[l] == s[r]) {
                    dp[l][r] = 2 + dp[l + 1][r - 1];
                } else {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static int longestPalindromeSubseq4(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n];
        for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {
            dp[l] = 1; // dp[l][l]
            if (l + 1 < n) {
                leftDown = dp[l + 1];
                dp[l + 1] = s[l] == s[l + 1] ? 2 : 1; // dp[l][l+1]
            }
            for (int r = l + 2; r < n; r++) {
                backup = dp[r];
                if (s[l] == s[r]) {
                    dp[r] = 2 + leftDown;
                } else {
                    dp[r] = Math.max(dp[r], dp[r - 1]);
                }
                leftDown = backup;
            }
        }
        return dp[n - 1]; // l == 0
    }
}
