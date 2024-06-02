package core.unionfind;

/**
 * https://leetcode.cn/problems/number-of-islands/
 */
public class NumberOfIslands {

    public static final int MAXN = 100_001;
    public static final int[] father = new int[MAXN];
    public static int cols, sets;

    public static int index(int x, int y) {
        return x * cols + y;
    }

    public static void build(int n, int m, char[][] grid) {
        cols = m;
        sets = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }

                int idx = index(i, j);
                father[idx] = idx;
                sets++;
            }
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x1, int y1, int x2, int y2) {
        int x = index(x2, y2), y = index(x1, y1);
        if (isSameSet(x, y)) {
            return;
        }
        father[find(x)] = find(y);
        sets--;
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        build(n, m, grid);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }

                if (j > 0 && grid[i][j - 1] == '1') {
                    union(i, j, i, j - 1);
                }
                if (i > 0 && grid[i - 1][j] == '1') {
                    union(i, j, i - 1, j);
                }
            }
        }
        return sets;
    }
}
