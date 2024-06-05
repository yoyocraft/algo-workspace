package core.dp_one_dimen;

/**
 * https://leetcode.cn/problems/ugly-number-ii/
 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2, i2 = 1, i3 = 1, i5 = 1, a, b, c, cur; i <= n; i++) {
            a = dp[i2] * 2;
            b = dp[i3] * 3;
            c = dp[i5] * 5;
            cur = min(a, b, c);
            dp[i] = cur;
            if (cur == a) {
                i2++;
            }
            if (cur == b) {
                i3++;
            }
            if (cur == c) {
                i5++;
            }
        }
        return dp[n];
    }

    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}