package core.two_way_bfs;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/closest-subsequence-sum/
 */
public class ClosestSubsequenceSum {

    public static final int MAXM = 1 << 20;
    public static final int[] lsum = new int[MAXM];
    public static final int[] rsum = new int[MAXM];
    public static int fillIdx;

    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        long positiveSum = 0, nagetiveSum = 0;
        for (int x : nums) {
            if (x > 0) {
                positiveSum += x;
            } else {
                nagetiveSum += x;
            }
        }
        if (positiveSum < goal) {
            return (int) (goal - positiveSum);
        }
        if (nagetiveSum > goal) {
            return (int) (nagetiveSum - goal);
        }

        Arrays.sort(nums);
        // 分左右两组 bfs
        fillIdx = 0;
        collect(nums, 0, n >> 1, 0, lsum);
        int lsize = fillIdx;
        fillIdx = 0;
        collect(nums, n >> 1, n, 0, rsum);
        int rsize = fillIdx;
        Arrays.sort(lsum, 0, lsize);
        Arrays.sort(rsum, 0, rsize);
        int ans = Math.abs(goal);
        for (int i = 0, j = rsize - 1; i < lsize; i++) {
            while (j > 0 && Math.abs(goal - lsum[i] - rsum[j - 1]) <= Math.abs(goal - lsum[i] - rsum[j])) {
                j--;
            }
            ans = Math.min(ans, Math.abs(goal - lsum[i] - rsum[j]));
        }
        return ans;
    }

    public static void collect(int[] nums, int idx, int end, int s, int[] sum) {
        if (idx == end) {
            sum[fillIdx++] = s;
        } else {
            // 相同元素分组
            int j = idx + 1;
            while (j < end && nums[j] == nums[idx]) {
                j++;
            }
            // 分组 bfs
            for (int k = 0; k <= j - idx; k++) {
                collect(nums, j, end, s + k * nums[idx], sum);
            }
        }
    }
}
