package core.slide_window;

public class GasStation {
    // nothing

    /**
     * https://leetcode.cn/problems/gas-station/
     */
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;

            for (int l = 0, r = 0, len = 0, sum = 0; l < n; l++) {
                while (sum >= 0) {
                    // 以 l 为起点可以走完全程
                    if (len == n) {
                        return l;
                    }

                    r = (l + (len++)) % n;
                    sum += gas[r] - cost[r]; // 累计油的余量
                }
                // 以 l 为起点不可以走完全程
                len--;
                sum -= gas[l] - cost[l];
            }
            return -1;
        }
    }
}