package core.unionfind;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column/
 */
public class MostStonesRemovedWithSameRowOrColumn {

    public static final int MAXN = 1_001;
    public static final int[] father = new int[MAXN];
    public static int sets;

    public static final Map<Integer /* 行号 */, Integer /* 第一次遇到的石头编号 */> rowFirst = new HashMap<>();
    public static final Map<Integer /* 列号 */, Integer /* 第一次遇到的石头编号 */> colFirst = new HashMap<>();

    public static void build(int n) {
        rowFirst.clear();
        colFirst.clear();
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        sets = n;
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            sets--;
        }
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        build(n);
        for (int i = 0; i < n; i++) {
            int row = stones[i][0];
            int col = stones[i][1];
            if (!rowFirst.containsKey(row)) {
                rowFirst.put(row, i);
            } else {
                union(i, rowFirst.get(row));
            }
            if (!colFirst.containsKey(col)) {
                colFirst.put(col, i);
            } else {
                union(i, colFirst.get(col));
            }
        }
        return n - sets;
    }

}