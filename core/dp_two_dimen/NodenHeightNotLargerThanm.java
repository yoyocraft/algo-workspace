package core.dp_two_dimen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/aaefe5896cce4204b276e213e725f3ea
 */
public class NodenHeightNotLargerThanm {

    public static final int MAXN = 51;
    public static final int MOD = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            out.println(solve1(n, m));
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long[][] memo = new long[MAXN][MAXN];

    public static void init() {
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                memo[i][j] = -1L;
            }
        }
    }

    static {
        init();
    }

    public static int solve1(int n, int m) {
        // base case
        if (n == 0) {
            return 1;
        }
        if (m == 0) {
            return 0;
        }

        if (memo[n][m] != -1) {
            return (int) memo[n][m];
        }
        long ans = 0;
        // 枚举左子树的节点个数, [0,n-1]
        for (int k = 0; k < n; k++) {
            ans = (ans + ((long) solve1(k, m - 1) * solve1(n - 1 - k, m - 1)) % MOD) % MOD;
        }
        return (int) (memo[n][m] = ans);
    }

    public static long[][] fullDp = new long[MAXN][MAXN];

    public static int solve2(int n, int m) {
        for (int j = 0; j <= m; j++) {
            fullDp[0][j] = 1L;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                fullDp[i][j] = 0L;
                for (int k = 0; k < i; k++) {
                    fullDp[i][j] = (fullDp[i][j] + fullDp[k][j - 1] * fullDp[i - 1 - k][j - 1] % MOD) % MOD;
                }
            }
        }
        return (int) fullDp[n][m];
    }

    public static long[] thinDp = new long[MAXN];

    public static int solve3(int n, int m) {
        thinDp[0] = 1; // dp[0][j]
        for (int i = 1; i <= n; i++) {
            thinDp[i] = 0;
        }

        for (int j = 1; j <= m; j++) {
            for (int i = n; i >= 1; i--) {
                thinDp[i] = 0;
                for (int k = 0; k < i; k++) {
                    thinDp[i] = (thinDp[i] + thinDp[k] * thinDp[i - 1 - k] % MOD) % MOD;
                }
            }
        }
        return (int) thinDp[n]; // dp[n][m]
    }
}