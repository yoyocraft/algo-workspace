package core.monotonic_stack;

/**
 * https://leetcode.cn/problems/maximum-width-ramp/
 */
public class MaximumWidthRamp {

    public static final int MAXN = 50_001;
    public static final int[] st = new int[MAXN];
    public static int top;

    public static void init() {
        top = -1;
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public int maxWidthRamp(int[] nums) {
        init();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (isEmpty() || nums[st[top]] > nums[i]) {
                st[++top] = i;
            }
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (!isEmpty() && nums[i] >= nums[st[top]]) {
                ans = Math.max(ans, i - st[top--]);
            }
        }
        return ans;
    }
}
