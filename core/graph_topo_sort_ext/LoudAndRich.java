package core.graph_topo_sort_ext;

import java.util.ArrayList;
import java.util.List;

public class LoudAndRich {
    /**
     * https://leetcode.cn/problems/loud-and-rich/
     */
    class Solution {
        public int[] loudAndRich(int[][] richer, int[] quiet) {
            int n = quiet.length;
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                g.add(new ArrayList<>());
            }

            int[] indgree = new int[n];
            for (int[] r : richer) {
                g.get(r[0]).add(r[1]);
                indgree[r[1]]++;
            }
            int[] que = new int[n];
            int l = 0, r = 0;
            for (int i = 0; i < n; i++) {
                if (indgree[i] == 0) {
                    que[r++] = i;
                }
            }

            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                ans[i] = i;
            }
            while (l < r) {
                int cur = que[l++];
                for (int nxt : g.get(cur)) {
                    if (quiet[ans[cur]] < quiet[ans[nxt]]) {
                        ans[nxt] = ans[cur];
                    }
                    if (--indgree[nxt] == 0) {
                        que[r++] = nxt;
                    }
                }
            }
            return ans;
        }
    }
}
