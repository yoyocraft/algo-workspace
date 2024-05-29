package core.monotonic_stack;

/**
 * https://leetcode.cn/problems/sum-of-subarray-minimums/
 */
public class SumOfSubarrayMinimums {
    public static final int MOD = (int) 1e9 + 7;
    public static final int MAXN = 100_001;
    public static final int[] st = new int[MAXN];
    public static int top;

    public static void init() {
        top = -1;
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public int sumSubarrayMins(int[] arr) {
        init();
        long ans = 0;

        for (int i = 0, cur; i < arr.length; i++) {
            while (!isEmpty() && arr[st[top]] >= arr[i]) {
                cur = st[top--];
                int left = isEmpty() ? -1 : st[top];
                ans = (ans + (long) (cur - left) * (i - cur) * arr[cur]) % MOD;
            }
            st[++top] = i;
        }

        while (!isEmpty()) {
            int cur = st[top--];
            int left = isEmpty() ? -1 : st[top];
            ans = (ans + (long) (cur - left) * (arr.length - cur) * arr[cur]) % MOD;
        }
        return (int) ans;
    }

}