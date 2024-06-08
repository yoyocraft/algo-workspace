package core.dp_two_dimen;

/**
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] memo = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, f(matrix, i, j, memo));
            }
        }
        return ans;
    }

    public static int f(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int pathLen = 0;
        if (i > 0 && matrix[i][j] < matrix[i - 1][j]) {
            pathLen = Math.max(pathLen, f(matrix, i - 1, j, memo));
        }
        if (i + 1 < matrix.length && matrix[i][j] < matrix[i + 1][j]) {
            pathLen = Math.max(pathLen, f(matrix, i + 1, j, memo));
        }
        if (j > 0 && matrix[i][j] < matrix[i][j - 1]) {
            pathLen = Math.max(pathLen, f(matrix, i, j - 1, memo));
        }
        if (j + 1 < matrix[0].length && matrix[i][j] < matrix[i][j + 1]) {
            pathLen = Math.max(pathLen, f(matrix, i, j + 1, memo));
        }
        return memo[i][j] = pathLen + 1;
    }
}