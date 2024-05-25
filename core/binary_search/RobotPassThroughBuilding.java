package core.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/7037a3d57bbd4336856b8e16a9cafd71
 */
public class RobotPassThroughBuilding {

    public static final int MAXN = 100_001;
    public static int[] arr = new int[MAXN];
    public static int n;

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            int l = -1, r = 0;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
                r = Math.max(r, arr[i]);
            }
            out.println(compute(l, r, r));
        }

        out.flush();
        out.close();
        br.close();
    }

    public static int compute(int l, int r, int mx) {
        int m = 0;
        while (l + 1 < r) {
            m = l + (r - l) / 2;
            if (check(m, mx)) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static boolean check(int e, int mx) {
        for (int i = 0; i <= n; i++) {
            if (e < arr[i]) {
                e -= arr[i] - e;
            } else {
                e += e - arr[i];
            }
            if (e > mx) { // 一定可以通关
                return true;
            }
            if (e < 0) {
                return false;
            }
        }
        return true;
    }
}
