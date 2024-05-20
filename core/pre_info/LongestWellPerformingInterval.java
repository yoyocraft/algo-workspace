package core.pre_info;

import java.util.HashMap;
import java.util.Map;

public class LongestWellPerformingInterval {
    // nothing

    /**
     * https://leetcode.cn/problems/longest-well-performing-interval/
     */
    class Solution {
        public int longestWPI(int[] hours) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int ans = 0;
            for (int i = 0, sum = 0; i < hours.length; i++) {
                sum += hours[i] > 8 ? 1 : -1;
                if (sum > 0) { // [0, i] 都是合法的
                    ans = i + 1;
                } else {
                    // sum <= 0
                    if (map.containsKey(sum - 1)) {
                        ans = Math.max(ans, i - map.get(sum - 1)); // 减去sum-1，余下的部分至少是1
                    }
                }
                if (!map.containsKey(sum)) {
                    map.put(sum, i); // 记录sum的最早位置
                }
            }
            return ans;
        }
    }

}