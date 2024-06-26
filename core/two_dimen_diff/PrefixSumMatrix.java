package core.two_dimen_diff;

public class PrefixSumMatrix {
    // nothing
}

/**
 * https://leetcode.cn/problems/range-sum-query-2d-immutable/
 */
class NumMatrix {

    int[][] sum;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        sum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
    }
}