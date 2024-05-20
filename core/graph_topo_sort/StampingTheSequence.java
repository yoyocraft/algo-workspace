package core.graph_topo_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StampingTheSequence {
    // nothing

    /**
     * https://leetcode.cn/problems/stamping-the-sequence/
     */
    class Solution {
        public int[] movesToStamp(String stamp, String target) {
            char[] s = stamp.toCharArray(), t = target.toCharArray();
            int m = s.length, n = t.length;
            int[] indgree = new int[n - m + 1]; // 盖印章的范围: [0, n-m+1]
            Arrays.fill(indgree, m);
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                g.add(new ArrayList<>());
            }

            int[] que = new int[n - m + 1];
            int l = 0, r = 0;
            for (int i = 0; i <= n - m; i++) {
                for (int j = 0; j < m; j++) {
                    if (t[i + j] == s[j]) {
                        if (--indgree[i] == 0) {
                            que[r++] = i;
                        }
                    } else {
                        // from: i+j, 错误的位置
                        // to: i, 以 i 开头的位置
                        g.get(i + j).add(i);
                    }
                }
            }

            boolean[] vis = new boolean[n];
            int[] path = new int[n - m + 1];
            int size = 0;
            while (l < r) {
                int cur = que[l++];
                path[size++] = cur;
                for (int i = 0; i < m; i++) {
                    if (vis[cur + i])
                        continue;
                    vis[cur + i] = true;
                    for (int nxt : g.get(cur + i)) {
                        if (--indgree[nxt] == 0) {
                            que[r++] = nxt;
                        }
                    }
                }
            }

            if (size != n - m + 1) {
                return new int[0];
            }
            // path 逆序处理
            for (int i = 0, j = size - 1; i < j; i++, j--) {
                int tmp = path[i];
                path[i] = path[j];
                path[j] = tmp;
            }
            return path;
        }
    }

}