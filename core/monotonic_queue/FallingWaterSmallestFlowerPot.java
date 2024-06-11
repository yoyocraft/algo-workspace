package core.monotonic_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * https://www.luogu.com.cn/problem/P2698
 */
public class FallingWaterSmallestFlowerPot {

    public static final int MAXN = 100_005;

    public static final int[][] arr = new int[MAXN][2];

    public static final int[] mxQue = new int[MAXN];
    public static int mxh, mxt;
    public static final int[] mnQue = new int[MAXN];
    public static int mnh, mnt;

    public static int n, d;

    public static int solve() {
        mxh = mxt = mnh = mnt = 0;

        Arrays.sort(arr, 0, n, (a, b) -> a[0] - b[0]);
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; l < n; l++) {
            while (!check() && r < n) {
                offer(r++);
            }
            if (check()) {
                ans = Math.min(ans, arr[r - 1][0] - arr[l][0]);
            }
            poll(l);
        }
        return ans;
    }

    public static boolean check() {
        int mx = mxh < mxt ? arr[mxQue[mxh]][1] : 0;
        int mn = mnh < mnt ? arr[mnQue[mnh]][1] : 0;
        return mx - mn >= d;
    }

    public static void offer(int r) {
        while (mxh < mxt && arr[r][1] >= arr[mxQue[mxt - 1]][1]) {
            mxt--;
        }
        mxQue[mxt++] = r;
        while (mnh < mnt && arr[r][1] <= arr[mnQue[mnt - 1]][1]) {
            mnt--;
        }
        mnQue[mnt++] = r;
    }

    public static void poll(int l) {
        if (mxh < mxt && mxQue[mxh] == l) {
            mxh++;
        }
        if (mnh < mnt && mnQue[mnh] == l) {
            mnh++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            d = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
            }

            int ans = solve();
            out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
        out.flush();
        out.close();
        br.close();
    }
}
