package core.double_pointer;

/**
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int lh = height[l], rh = height[r];
            ans = Math.max(ans, (r - l) * Math.min(lh, rh));
            if (lh < rh) {
                while (l < r && height[l] <= lh) {
                    l++;
                }
            } else {
                while (l < r && height[r] <= rh) {
                    r--;
                }
            }
        }
        return ans;
    }
}
