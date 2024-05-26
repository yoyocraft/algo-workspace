package core.graph_topo_sort;

public class MaximumEmployeesToBeInvitedToAMeeting {
    /**
     * https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/
     */
    class Solution {
        public int maximumInvitations(int[] favorite) {
            // favorite[a] = b: a -> b
            int n = favorite.length;
            int[] indegree = new int[n];
            for (int i = 0; i < n; i++) {
                indegree[favorite[i]]++;
            }
            int[] que = new int[n];
            int l = 0, r = 0;
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    que[r++] = i;
                }
            }

            // deep[i]: 不包括节点 i 在内, i 之前的最长链的长度
            int[] deep = new int[n];
            while (l < r) {
                int cur = que[l++];
                int nxt = favorite[cur];
                deep[nxt] = Math.max(deep[nxt], deep[cur] + 1);
                if (--indegree[nxt] == 0) {
                    que[r++] = nxt;
                }
            }
            // 至此，图中只剩下环
            // 情况1: 小环，即中心环节点个数为2 => sum(中心点个数 + 延伸点个数)
            int sumOfSmallRings = 0;
            // 情况2: 大环，即中心环个数超过2 => max(中心点个数)
            int bigRings = 0;
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    continue;
                }
                // 只关心环
                int ringSize = 1;
                indegree[i] = 0;
                for (int j = favorite[i]; j != i; j = favorite[j]) {
                    ringSize++;
                    indegree[j] = 0;
                }
                if (ringSize == 2) {
                    sumOfSmallRings += 2 + deep[i] + deep[favorite[i]];
                } else {
                    bigRings = Math.max(bigRings, ringSize);
                }
            }
            return Math.max(sumOfSmallRings, bigRings);
        }
    }
}
