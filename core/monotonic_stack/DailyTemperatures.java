package core.monotonic_stack;

/**
 * https://leetcode.cn/problems/daily-temperatures/
 */
public class DailyTemperatures {

    public static final int MAXN = 100_001;
    public static final int[] st = new int[MAXN];
    public static int top;

    public static void init() {
        top = -1;
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        init();
        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = 0, idx; i < n; i++) {
            while (!isEmpty() && temperatures[st[top]] < temperatures[i]) {
                idx = st[top--];
                ans[idx] = i - idx;
            }
            st[++top] = i;
        }
        return ans;
    }

}
