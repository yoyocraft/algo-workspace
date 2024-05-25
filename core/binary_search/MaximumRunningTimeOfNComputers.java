package core.binary_search;

/**
 * https://leetcode.cn/problems/maximum-running-time-of-n-computers/
 */
public class MaximumRunningTimeOfNComputers {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int x : batteries) {
            sum += x;
        }
        long l = 0L, r = sum / n;
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            if (check(batteries, mid, n)) {
                l = mid; // l 为目前的最长时间
            } else {
                r = mid;
            }
        }
        return l;
    }

    public long maxRunTimePlus(int n, int[] batteries) {
        long sum = 0, mx = 0;
        for (int x : batteries) {
            sum += x;
            mx = Math.max(mx, x);
        }
        if (sum >= n * mx) {
            return sum / n;
        }
        long l = 0L, r = mx; // 优化二分边界
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            if (check(batteries, mid, n)) {
                l = mid; // l 为目前的最长时间
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static boolean check(int[] batteries, long time, long n) {
        long sum = 0;
        for (int x : batteries) {
            sum += Math.min(x, time);
        }
        return sum >= n * time;
    }
}
