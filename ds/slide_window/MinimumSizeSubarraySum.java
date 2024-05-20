package ds.slide_window;

public class MinimumSizeSubarraySum {
    // nothing

    /**
     * https://leetcode.cn/problems/minimum-size-subarray-sum/
     */
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int ans = Integer.MAX_VALUE;
            for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
                sum += nums[r];
                // 收缩窗口
                while (sum - nums[l] >= target) {
                    sum -= nums[l++];
                }
                if (sum >= target) {
                    ans = Math.min(ans, r - l + 1);
                }
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }

}