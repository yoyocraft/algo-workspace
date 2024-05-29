package core.monotonic_stack;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/maximal-rectangle/
 */
public class MaximalRectangle {

    public static final int MAXN = 204;
    public static final int[] heights = new int[MAXN];
    public static final int[] hs = new int[MAXN];
    public static final int[] st = new int[MAXN];
    public static int top, n, m;

    public static void init0() {
        top = -1;
    }

    public static void init() {
        Arrays.fill(heights, 0, m, 0);
        hs[0] = 0;
        hs[m + 1] = 0;
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public static int largestRectangleArea(int[] heights) {
        init0();
        System.arraycopy(heights, 0, hs, 1, m);
        int area = 0;
        for (int i = 0; i < m + 2; i++) {
            while (!isEmpty() && hs[st[top]] > hs[i]) {
                int h = hs[st[top--]];
                area = Math.max(area, h * (i - st[top] - 1));
            }
            st[++top] = i;
        }
        return area;
    }

    public int maximalRectangle(char[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        init();
        int mxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }
            mxArea = Math.max(mxArea, largestRectangleArea(heights));
        }
        return mxArea;
    }
}
