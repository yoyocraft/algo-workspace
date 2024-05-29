package core.monotonic_stack;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram
 */
public class LongestRectangleInHistogram {

    public static final int MAXN = 100_003;
    public static final int[] st = new int[MAXN];
    public static int top;

    public static void init() {
        top = -1;
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public int largestRectangleArea(int[] heights) {
        init();
        // 头尾补充两个 0
        int[] hs = new int[heights.length + 2];
        System.arraycopy(heights, 0, hs, 1, heights.length);

        int area = 0;
        for (int i = 0; i < hs.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左侧第一个小于自身的柱体」
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了
            while (!isEmpty() && hs[st[top]] > hs[i]) {
                int h = hs[st[top--]];
                area = Math.max(area, (i - st[top] - 1) * h);
            }
            st[++top] = i;
        }
        return area;
    }
}
