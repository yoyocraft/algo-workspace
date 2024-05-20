package core.graph_topo_sort_ext;

import java.util.ArrayList;
import java.util.List;

public class ParallelCoursesIII {

    /**
     * https://leetcode.cn/problems/parallel-courses-iii/
     */
    class Solution {
        public int minimumTime(int n, int[][] relations, int[] time) {
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                g.add(new ArrayList<>());
            }
            int[] indgree = new int[n + 1];
            for (int[] e : relations) {
                g.get(e[0]).add(e[1]);
                indgree[e[1]]++;
            }
            int[] que = new int[n];
            int l = 0, r = 0;
            for (int i = 1; i <= n; i++) {
                if (indgree[i] == 0) {
                    que[r++] = i;
                }
            }

            int[] cost = new int[n + 1];
            int ans = 0;
            while (l < r) {
                int cur = que[l++];
                cost[cur] += time[cur - 1];
                ans = Math.max(ans, cost[cur]);
                for (int nxt : g.get(cur)) {
                    // 推进最大值
                    cost[nxt] = Math.max(cost[nxt], cost[cur]);
                    if (--indgree[nxt] == 0) {
                        que[r++] = nxt;
                    }
                }
            }
            return ans;
        }
    }
}
