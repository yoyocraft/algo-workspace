package core.quick_pow;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }

        long[][] base = {
                { 1, 1 },
                { 1, 0 }
        };
        long[][] ans = {
                { 1, 1 }
        };
        ans = multiply(ans, power(base, n - 1));
        return (int) ans[0][0];
    }

    public static long[][] power(long[][] m, int p) {
        long[][] ans = new long[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            ans[i][i] = 1;
        }
        for (; p != 0; p >>>= 1) {
            if ((p & 1) == 1) {
                ans = multiply(ans, m);
            }
            m = multiply(m, m);
        }
        return ans;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        int r = a.length, c = b[0].length, z = b.length;
        long[][] ans = new long[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < z; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }
}
