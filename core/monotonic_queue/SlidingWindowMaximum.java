package core.monotonic_queue;

/**
 * https://leetcode.cn/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {

    public static final int MAXN = 100_001;
    public static final int[] que = new int[MAXN];
    public static int front, rear;

    public static boolean isEmpty() {
        return front == rear;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        front = rear = 0;
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i < n; i++) {
            // 1. 入队列，保持单调递减
            while (!isEmpty() && nums[i] >= nums[que[rear - 1]]) {
                rear--;
            }
            que[rear++] = i;
            // 2. 出队列
            if (i - que[front] >= k) {
                front++;
            }
            // 3. 记录答案
            if (i >= k - 1) {
                ans[i - k + 1] = nums[que[front]];
            }
        }

        return ans;
    }
}
