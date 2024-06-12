package core.greedy;

/**
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // 如果 mx > nums[i] => i 位置数非法
        // 从左向右遍历，记录最右的非法位置
        int right = -1;
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (mx > nums[i]) {
                right = i;
            }
            mx = Math.max(mx, nums[i]);
        }
        // 如果 mn < nums[i] => i 位置数非法
        // 从右向左遍历，记录最左的非法位置
        int left = n;
        int mn = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (mn < nums[i]) {
                left = i;
            }
            mn = Math.min(mn, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }
}
