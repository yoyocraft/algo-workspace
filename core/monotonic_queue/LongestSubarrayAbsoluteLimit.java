package core.monotonic_queue;

/**
 * https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
public class LongestSubarrayAbsoluteLimit {

    public static final int MAXN = 100_001;
    public static final int[] mnQue = new int[MAXN];
    public static int mnh, mnt;

    public static final int[] mxQue = new int[MAXN];
    public static int mxh, mxt;

    public static int[] nums;

    public static boolean check(int limit, int num) {
        // num 进入窗口之后的最大值
        int mx = mxh < mxt ? Math.max(nums[mxQue[mxh]], num) : num;
        // num 进入窗口之后的最小值
        int mn = mnh < mnt ? Math.min(nums[mnQue[mnh]], num) : num;
        return mx - mn <= limit;
    }

    public static void offer(int r) {
        while (mxh < mxt && nums[r] >= nums[mxQue[mxt - 1]]) {
            mxt--;
        }
        mxQue[mxt++] = r;
        while (mnh < mnt && nums[r] <= nums[mnQue[mnt - 1]]) {
            mnt--;
        }
        mnQue[mnt++] = r;
    }

    public static void poll(int l) {
        if (mxh < mxt && mxQue[mxh] == l) {
            mxh++;
        }
        if (mnh < mnt && mnQue[mnh] == l) {
            mnh++;
        }
    }

    public int longestSubarray(int[] arr, int limit) {
        mxh = mxt = mnh = mnt = 0;
        nums = arr;

        int n = arr.length;
        int ans = 0;
        for (int l = 0, r = 0; l < n; l++) {
            // [l, r)
            while (r < n && check(limit, nums[r])) {
                offer(r++);
            }
            // [l, r) 是以 l 开头可以延伸的最长范围
            ans = Math.max(ans, r - l);
            poll(l);
        }
        return ans;
    }
}
