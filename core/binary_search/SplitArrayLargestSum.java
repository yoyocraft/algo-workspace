package core.binary_search;

/**
 * https://leetcode.cn/problems/split-array-largest-sum/
 */
public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int k) {
        long sum = 0;
        for (int x : nums) {
            sum += x;
        }

        long l = -1, r = sum, mid;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (check(nums, mid, k)) {
                r = mid;
            } else {
                l = mid;
            }

        }
        return (int) r;
    }

    public static boolean check(int[] nums, long limit, int k) {
        int s = 0, parts = 1;
        for (int x : nums) {
            if (x > limit) {
                return false;
            }
            if (s + x > limit) {
                s = x;
                if (++parts > k) {
                    return false;
                }
            } else {
                s += x;
            }
        }
        return true;
    }

}
