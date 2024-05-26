package core.mst;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths/
 */
public class CheckingExistenceOfEdgeLengthLimit {

    public static int MAXN = 100_001;
    public static int[][] questions = new int[MAXN][4];
    public static int[] father = new int[MAXN];

    public static void build(int n) {
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

    public static void union(int x, int y) {
        father[find(x)] = find(y);
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        int m = edgeList.length, k = queries.length;
        for (int i = 0; i < k; i++) {
            questions[i][0] = queries[i][0];
            questions[i][1] = queries[i][1];
            questions[i][2] = queries[i][2];
            questions[i][3] = i;
        }

        Arrays.sort(questions, 0, k, (a, b) -> a[2] - b[2]);
        build(n);
        boolean[] ans = new boolean[k];
        for (int i = 0, j = 0; i < k; i++) {
            for (; j < m && edgeList[j][2] < questions[i][2]; j++) {
                union(edgeList[j][0], edgeList[j][1]);
            }
            ans[questions[i][3]] = isSameSet(questions[i][0], questions[i][1]);
        }
        return ans;
    }

}