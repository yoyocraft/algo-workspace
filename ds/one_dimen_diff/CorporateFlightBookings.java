package ds.one_dimen_diff;

public class CorporateFlightBookings {
    // nothing
}

/**
 * https://leetcode.cn/problems/corporate-flight-bookings/
 */
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 2];
        // 设置差分数组
        for(int[] book : bookings) {
            diff[book[0]] += book[2];
            diff[book[1] + 1] -= book[2];
        }

        // 加工前缀和
        for(int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1];
        }
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = diff[i + 1];
        }
        return ans;
    }
}