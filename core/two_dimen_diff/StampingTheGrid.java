package core.two_dimen_diff;

/**
 * https://leetcode.cn/problems/stamping-the-grid/
 */
public class StampingTheGrid {
    // nothing

    public boolean possibleToStamp(int[][] grid, int h, int w) {
        int n = grid.length, m = grid[0].length;
        // 1. 计算 grid 数组的前缀和，便于后面判断区域是否可以贴邮票
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
            }
        }

        // 2. 计算二维差分
        // 为方便第 3 步的计算，在 d 数组的最上面和最左边各加了一行（列），所以下标要 +1
        int[][] diff = new int[n + 2][m + 2];
        for (int x2 = h; x2 <= n; x2++) {
            for (int y2 = w; y2 <= m; y2++) {
                int x1 = x2 - h + 1, y1 = y2 - w + 1;
                // (x1, y1) 作为左上角可以贴邮票
                if (query(sum, x1, y1, x2, y2) == 0) {
                    add(diff, x1, y1, x2, y2, 1);
                }
            }
        }
        build(diff, n, m);
        // 3. 检查所有格子
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 原始矩阵里：grid[i][j] == 0，说明是个洞
                // 差分矩阵里：diff[i + 1][j + 1] == 0，说明洞上并没有邮票
                if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int query(int[][] sum, int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1];
    }

    public static void add(int[][] diff, int x1, int y1, int x2, int y2, int v) {
        diff[x1][y1] += v;
        diff[x1][y2 + 1] -= v;
        diff[x2 + 1][y1] -= v;
        diff[x2 + 1][y2 + 1] += v;
    }

    public static void build(int[][] diff, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                diff[i + 1][j + 1] += diff[i][j + 1] + diff[i + 1][j] - diff[i][j];
            }
        }
    }

}