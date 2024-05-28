package core.quick_pow;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 */
public class FibonacciNumber {

    public int fib(int n) {
        if (n < 3) {
            return n;
        }

        long[][] start = { { 1, 1 } }; // f1, f0
        long[][] base = {
                { 1, 1 }, // f1 + f0
                { 1, 0 } // f1
        };

        long[][] ans = multiply(start, power(base, n - 1));
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
