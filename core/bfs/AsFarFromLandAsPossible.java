package core.bfs;

/**
 * https://leetcode.cn/problems/as-far-from-land-as-possible/
 */
public class AsFarFromLandAsPossible {

    public static final int MAXN = 101;
    public static final int MAXM = 101;
    public static int[][] que = new int[MAXN * MAXM][2];
    public static int l, r;
    public static boolean[][] visited = new boolean[MAXN][MAXM];
    public static int[] move = new int[] { -1, 0, 1, 0, -1 };

    public int maxDistance(int[][] grid) {
        l = r = 0;
        int n = grid.length;
        int m = grid[0].length;
        int seas = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    que[r][0] = i;
                    que[r++][1] = j;
                } else {
                    visited[i][j] = false;
                    seas++;
                }
            }
        }
        if (seas == 0 || seas == n * m) {
            return -1;
        }

        int level = 0;
        while (l < r) {
            level++;
            int size = r - l;
            for (int k = 0, x, y, nx, ny; k < size; k++) {
                x = que[l][0];
                y = que[l++][1];
                for (int i = 0; i < 4; i++) {
                    nx = x + move[i];
                    ny = y + move[i + 1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        que[r][0] = nx;
                        que[r++][1] = ny;
                    }
                }
            }
        }
        return level - 1;
    }
}
