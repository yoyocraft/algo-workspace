package core.two_way_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * https://www.luogu.com.cn/problem/P4799
 */
public class SnacksWaysBuyTickets {

    public static final int MAXN = 40;
    public static final int MAXM = 1 << 20;
    public static final long[] arr = new long[MAXN];
    // 叶子结点结果
    public static final long[] lsum = new long[MAXM];
    public static final long[] rsum = new long[MAXM];
    public static int n;
    public static long w;

    public static long solve() {
        // 划分左右部分，分别 bfs
        int lsize = f(0, n >> 1, 0, w, lsum, 0);
        int rsize = f(n >> 1, n, 0, w, rsum, 0);
        Arrays.sort(lsum, 0, lsize);
        Arrays.sort(rsum, 0, rsize);
        long ans = 0L;
        // 双指针
        for (int i = lsize - 1, j = 0; i >= 0; i--) {
            while (j < rsize && lsum[i] + rsum[j] <= w) {
                j++;
            }
            ans += j;
        }
        return ans;
    }

    public static int f(int idx, int end, long s, long w, long[] ans, int fill) {
        if (s > w) {
            return fill; // 当前填写到的位置
        }

        if (idx == end) {
            ans[fill++] = s;
        } else {
            fill = f(idx + 1, end, s, w, ans, fill);
            fill = f(idx + 1, end, s + arr[idx], w, ans, fill);
        }
        return fill;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            w = (long) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (long) in.nval;
            }
            out.println(solve());
        }
        out.flush();
        out.close();
        br.close();
    }
}
