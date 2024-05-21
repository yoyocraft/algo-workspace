package core.greedy_01;

import java.util.Arrays;

public class TwoCityScheduling {

    /**
     * https://leetcode.cn/problems/two-city-scheduling/
     */
    class Solution {
        public int twoCitySchedCost(int[][] costs) {
            int n = costs.length;
            int[] diff = new int[n]; // 记录前往 a,b 地的差值
            int sum = 0;
            for (int i = 0; i < n; i++) {
                diff[i] = costs[i][1] - costs[i][0];
                sum += costs[i][0]; // 全部前往 a 地
            }
            Arrays.sort(diff);
            int m = n / 2;
            for (int i = 0; i < m; i++) {
                sum += diff[i];
            }
            return sum;
        }
    }
}
