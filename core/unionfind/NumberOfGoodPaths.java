package core.unionfind;

import java.util.Arrays;

public class NumberOfGoodPaths {

    public static final int MAXN = 30_001;
    public static int[] father = new int[MAXN];
    public static int[] maxCnt = new int[MAXN];

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            maxCnt[i] = 1;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static int union(int x, int y, int[] vals) {
        // fx: x 所在集合的代表节点，同时也是 x 所在集合的最大值下标
        int fx = find(x);
        // fy: y 所在集合的代表节点，同时也是 y 所在集合的最大值下标
        int fy = find(y);
        int pathNum = 0;
        if (vals[fx] > vals[fy]) {
            father[fy] = fx;
        } else if (vals[fx] < vals[fy]) {
            father[fx] = fy;
        } else {
            pathNum = maxCnt[fx] * maxCnt[fy];
            father[fx] = fy;
            maxCnt[fy] += maxCnt[fx];
        }
        return pathNum;
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        build(n);
        Arrays.sort(edges, (a, b) -> max(a, vals) - max(b, vals));
        int ans = n;
        for (int[] e : edges) {
            ans += union(e[0], e[1], vals);
        }
        return ans;
    }

    // 处理边的时候，依次从小节点往大节点处理
    public static int max(int[] e, int[] vals) {
        return Math.max(vals[e[0]], vals[e[1]]);
    }

    public static void main(String[] args) {
        NumberOfGoodPaths instance = new NumberOfGoodPaths();
        // 课上例子1
        // 0 1 2 3 4 5 6 7
        int[] vals1 = { 2, 1, 1, 2, 2, 1, 1, 2 };
        int[][] edges1 = {
                { 0, 1 },
                { 0, 2 },
                { 1, 3 },
                { 2, 4 },
                { 2, 5 },
                { 5, 6 },
                { 6, 7 } };
        System.out.println(instance.numberOfGoodPaths(vals1, edges1));

        // 课上例子2
        // 0 1 2 3 4 5 6 7 8 9 10 11 12
        int[] vals2 = { 1, 2, 2, 3, 1, 2, 2, 1, 1, 3, 3, 3, 3 };
        int[][] edges2 = {
                { 0, 1 },
                { 0, 2 },
                { 0, 3 },
                { 1, 4 },
                { 4, 7 },
                { 4, 8 },
                { 3, 5 },
                { 3, 6 },
                { 6, 9 },
                { 6, 10 },
                { 6, 11 },
                { 9, 12 } };
        System.out.println(instance.numberOfGoodPaths(vals2, edges2));
    }
}