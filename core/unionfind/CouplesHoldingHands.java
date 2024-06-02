package core.unionfind;

/**
 * https://leetcode.cn/problems/couples-holding-hands/
 */
public class CouplesHoldingHands {

    public static int MAXN = 31;
    public static int[] father = new int[MAXN];
    public static int sets; // 集合个数

    public static void build(int m) {
        sets = m;
        for (int i = 0; i < m; i++) {
            father[i] = i;
        }
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

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        build(n / 2);
        for (int i = 0; i < n; i += 2) {
            union(row[i] / 2, row[i + 1] / 2); // 相邻的人入集合，每一对情侣都有编号
        }
        return n / 2 - sets;
    }
}
