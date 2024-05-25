package core.binary_search;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
 */
public class FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = -1, r = nums[n - 1] - nums[0]; // 绝对差值上下界
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, mid, k)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static boolean check(int[] nums, int limit, int k) {
        int cnt = 0;
        for (int l = 0, r = 0; l < nums.length; l++) {
            r = l;
            while (r + 1 < nums.length && nums[r + 1] - nums[l] <= limit) {
                r++;
            }
            cnt += r - l;
            if (cnt >= k) {
                return true;
            }
        }
        return false;
    }

}
