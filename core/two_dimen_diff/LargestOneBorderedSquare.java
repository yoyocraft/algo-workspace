package core.two_dimen_diff;

public class LargestOneBorderedSquare {

}

/**
 * https://leetcode.cn/problems/largest-1-bordered-square/
 */
class Solution {

    public static void build(int n, int m, int[][] g, int[][] sum) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + g[i][j];
            }
        }
    }

    public static int query(int[][] sum, int r1, int c1, int r2, int c2) {
        return sum[r2 + 1][c2 + 1] - sum[r2 + 1][c1] - sum[r1][c2 + 1] + sum[r1][c1];
    }

    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] sum = new int[n + 1][m + 1];
        build(n, m, grid, sum);
        if (query(sum, 0, 0, n - 1, m - 1) == 0) {
            return 0;
        }
        int ans = 1;
        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < m; c1++) {
                // 当前正方形边长最小是ans, 从ans开始
                for (int r2 = r1 + ans, c2 = c1 + ans, k = ans + 1; r2 < n && c2 < m; r2++, c2++, k++) {
                    // (r1, c1) -> (r2, c2) 整个正方形，减去中间部分，得到四条边的和，若等于周长，则合法
                    if (query(sum, r1, c1, r2, c2) - query(sum, r1 + 1, c1 + 1, r2 - 1, c2 - 1) == (k - 1) << 2) {
                        ans = k;
                    }
                }
            }
        }
        return ans * ans;
    }
}