package core.dp_two_dimen;

/**
 * https://leetcode.cn/problems/minimum-path-sum/
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        return minPathSum1(grid);
    }

    public static int minPathSum1(int[][] grid) {
        return f1(grid, grid.length, grid[0].length);
    }

    public static int f1(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        int up = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            up = f1(grid, i - 1, j);
        }
        if (j - 1 >= 0) {
            left = f1(grid, i, j - 1);
        }
        return grid[i][j] + Math.min(up, left);
    }

    public static int minPathSum2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }
        return f2(grid, grid.length - 1, grid[0].length - 1, memo);
    }

    public static int f2(int[][] grid, int i, int j, int[][] memo) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int ans;
        if (i == 0 && j == 0) {
            ans = grid[0][0];
        } else {
            int up = Integer.MAX_VALUE;
            int left = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                up = f2(grid, i - 1, j, memo);
            }
            if (j - 1 >= 0) {
                left = f2(grid, i, j - 1, memo);
            }
            ans = grid[i][j] + Math.min(up, left);
        }
        memo[i][j] = ans;
        return ans;
    }

    public static int minPathSum3(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    public static int minPathSum4(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // 先让dp表，变成想象中的表的第0行的数据
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            // i = 1，dp表变成想象中二维表的第1行的数据
            // i = 2，dp表变成想象中二维表的第2行的数据
            // i = 3，dp表变成想象中二维表的第3行的数据
            // ...
            // i = n-1，dp表变成想象中二维表的第n-1行的数据
            dp[0] += grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[m - 1];
    }

}
