package ds.two_dimen_diff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.luogu.com.cn/problem/P3397
 */
public class DiffMatrixLuogu {
    public static final int MAXN = 1_002;
    public static int[][] diff = new int[MAXN][MAXN];
    public static int n, m;

    public static void add(int x1, int y1, int x2, int y2, int v) {
        diff[x1][y1] += v; // 左上角
        diff[x2 + 1][y1] -= v; // 左下角
        diff[x1][y2 + 1] -= v; // 右上角
        diff[x2 + 1][y2 + 1] += v; // 右下角
    }

    public static void build() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i][j + 1] + diff[i + 1][j] - diff[i][j];
            }
        }
    }

    public static void clear() {
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                diff[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for (int i = 0, x1, y1, x2, y2; i < m; i++) {
                in.nextToken();
                x1 = (int) in.nval;
                in.nextToken();
                y1 = (int) in.nval;
                in.nextToken();
                x2 = (int) in.nval;
                in.nextToken();
                y2 = (int) in.nval;
                add(x1, y1, x2, y2, 1);
            }
            build();
            for (int i = 1; i <= n; i++) {
                out.print(diff[i][1]);
                for (int j = 2; j <= n; j++) {
                    out.print(" " + diff[i][j]);
                }
                out.println();
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
