package core.monotonic_stack;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/count-submatrices-with-all-ones/
 */
public class CountSubmatricesWithAllOnes {
    public static final int MAXN = 151;
    public static int[] heights = new int[MAXN];
    public static int[] hs = new int[MAXN + 2];
    public static int[] st = new int[MAXN];
    public static int top;
    public static int n, m;

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

    // 比如
	//              1
	//              1
	//              1         1
	//    1         1         1
	//    1         1         1
	//    1         1         1
	//             
	//    3  ....   6   ....  8
	//   left      cur        i
	// 如上图，假设6位置从栈中弹出，6位置的高度为6(上面6个1)
	// 6位置的左边、离6位置最近、且小于高度6的是3位置(left)，3位置的高度是3
	// 6位置的右边、离6位置最近、且小于高度6的是8位置(i)，8位置的高度是4
	// 此时我们求什么？
	// 1) 求在4~7范围上必须以高度6作为高的矩形有几个？
	// 2) 求在4~7范围上必须以高度5作为高的矩形有几个？
	// 也就是说，<=4的高度一律不求，>6的高度一律不求！
	// 其他位置也会从栈里弹出，等其他位置弹出的时候去求！
	// 那么在4~7范围上必须以高度6作为高的矩形有几个？如下：
	// 4..4  4..5  4..6  4..7
	// 5..5  5..6  5..7
	// 6..6  6..7
	// 7..7
	// 10个！什么公式？
	// 4...7范围的长度为4，那么数量就是 : 4*5/2
	// 同理在4~7范围上，必须以高度5作为高的矩形也是这么多
	// 所以cur从栈里弹出时产生的数量 : 
	// (cur位置的高度-Max{left位置的高度,i位置的高度}) * ((i-left-1)*(i-left)/2)
    public static int countFromBottom() {
        init0();
        // 头尾多补两个 0
        System.arraycopy(heights, 0, hs, 1, m);
        int ans = 0;
        for (int i = 0, left, len, bottom; i < m + 2; i++) {
            while (!isEmpty() && hs[st[top]] >= hs[i]) {
                int cur = st[top--];
                if (hs[cur] == hs[i]) {
                    continue;
                }
                left = st[top];
                len = i - left - 1;
                bottom = Math.max(hs[left], hs[i]);
                ans += (hs[cur] - bottom) * len * (len + 1) / 2;
            }
            st[++top] = i;
        }
        return ans;
    }

    public int numSubmat(int[][] mat) {
        n = mat.length;
        m = mat[0].length;
        init();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            ans += countFromBottom();
        }
        return ans;
    }

}
