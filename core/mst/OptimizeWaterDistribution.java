package core.mst;

import java.util.Arrays;

/**
 * 水资源分配优化
 * 村里面一共有 n 栋房子。我们希望通过建造水井和铺设管道来为所有房子供水。
 * 对于每个房子 i，我们有两种可选的供水方案：一种是直接在房子内建造水井
 * 成本为 wells[i - 1] （注意 -1 ，因为 索引从0开始 ）
 * 另一种是从另一口井铺设管道引水，数组 pipes 给出了在房子间铺设管道的成本，
 * 其中每个 pipes[j] = [house1j, house2j, costj]
 * 代表用管道将 house1j 和 house2j 连接在一起的成本。连接是双向的。
 * 请返回 为所有房子都供水的最低总成本
 * 
 * https://leetcode.cn/problems/optimize-water-distribution-in-a-village/
 */
public class OptimizeWaterDistribution {

    public static int MAXN = 10_010;

    public static int[][] edges = new int[MAXN << 1][3];

    public static int cnt;

    public static int[] father = new int[MAXN];

    public static void build(int n) {
        cnt = 0;
        for (int i = 0; i <= n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static boolean union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            return true;
        }
        return false;
    }

    // mock 一个水源点，题目等价于这个水源点和所有村庄都连通的最小成本 => 最小生成树问题
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        build(n);
        for (int i = 0; i < n; i++, cnt++) {
            edges[cnt][0] = 0; // mock node
            edges[cnt][1] = i + 1;
            edges[cnt][2] = wells[i];
        }

        for (int i = 0; i < pipes.length; i++, cnt++) {
            edges[cnt][0] = pipes[i][0];
            edges[cnt][1] = pipes[i][1];
            edges[cnt][2] = pipes[i][2];
        }

        Arrays.sort(edges, 0, cnt, (a, b) -> a[2] - b[2]);
        int ans = 0;
        for (int i = 0; i < cnt; i++) {
            if (union(edges[i][0], edges[i][1])) {
                ans += edges[i][2];
            }
        }
        return ans;
    }
}
