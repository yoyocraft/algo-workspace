package core.double_pointer;

/**
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int n = height.length;
        int l = 0, lmx = height[0];
        int r = n - 1, rmx = height[n - 1];
        int ans = 0;
        while (l <= r) {
            if (lmx <= rmx) {
                ans += Math.max(0, lmx - height[l]);
                lmx = Math.max(lmx, height[l++]);
            } else {
                ans += Math.max(0, rmx - height[r]);
                rmx = Math.max(rmx, height[r--]);
            }
        }
        return ans;
    }
}
