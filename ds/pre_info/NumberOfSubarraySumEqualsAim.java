package ds.pre_info;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubarraySumEqualsAim {
    // nothing

    /**
     * https://leetcode.cn/problems/subarray-sum-equals-k/
     */
    class Solution {
        public int subarraySum(int[] nums, int aim) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1); // nums[i] == k 的情况
            int ans = 0;
            for (int i = 0, sum = 0; i < nums.length; i++) {
                sum += nums[i];
                ans += map.getOrDefault(sum - aim, 0);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return ans;
        }
    }

}