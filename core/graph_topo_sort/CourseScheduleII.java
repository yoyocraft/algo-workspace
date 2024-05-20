package core.graph_topo_sort;

import java.util.ArrayList;
import java.util.List;

/**
 * TopoSortDynamicLeetcode
 */
public class CourseScheduleII {
    // nothing

    /**
     * https://leetcode.cn/problems/course-schedule-ii/
     */
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                g.add(new ArrayList<>());
            }

            // 记录入度
            int[] indegree = new int[numCourses];
            for (int[] e : prerequisites) {
                g.get(e[1]).add(e[0]);
                indegree[e[0]]++;
            }

            int[] que = new int[numCourses];
            int l = 0, r = 0;
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    que[r++] = i;
                }
            }

            int cnt = 0;
            while (l < r) {
                int cur = que[l++];
                cnt++;
                for (int next : g.get(cur)) {
                    if (--indegree[next] == 0) {
                        que[r++] = next;
                    }
                }
            }
            return cnt == numCourses ? que : new int[0];
        }
    }

}