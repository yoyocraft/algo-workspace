package core.monotonic_stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
 */
public class LeftRightLess {

    public static final int MAXN = 1_000_001;
    public static int[] arr = new int[MAXN];
    public static int[] st = new int[MAXN];
    public static int[][] ans = new int[MAXN][2];
    public static int n, top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            solve();
            for (int i = 0; i < n; i++) {
                out.println(ans[i][0] + " " + ans[i][1]);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void solve() {
        top = -1;
        int cur;
        for (int i = 0; i < n; i++) {
            while (!isEmpty() && arr[st[top]] >= arr[i]) {
                cur = st[top--];
                ans[cur][0] = !isEmpty() ? st[top] : -1;
                ans[cur][1] = i;
            }
            st[++top] = i;
        }

        while (!isEmpty()) {
            cur = st[top--];
            ans[cur][0] = !isEmpty() ? st[top] : -1;
            ans[cur][1] = -1;
        }

        // 修正阶段， e.g: [5, 3, 3, 6, 1]
        for (int i = n - 2; i >= 0; i--) {
            if (ans[i][1] != -1 && arr[ans[i][1]] == arr[i]) {
                ans[i][1] = ans[ans[i][1]][1];
            }
        }
    }

    public static boolean isEmpty() {
        return top == -1;
    }
}
