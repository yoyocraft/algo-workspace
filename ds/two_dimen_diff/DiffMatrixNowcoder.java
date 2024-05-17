package ds.two_dimen_diff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/50e1a93989df42efb0b1dec386fb4ccc
 */
public class DiffMatrixNowcoder {

    public static final int MAXN = 1_005;
    public static final int MAXM = 1_005;
    public static long[][] diff = new long[MAXN][MAXM];
    public static int n, m, q;

    public static void add(int x1, int y1, int x2, int y2, int v) {
        diff[x1][y1] += v;
        diff[x1][y2 + 1] -= v;
        diff[x2 + 1][y1] -= v;
        diff[x2 + 1][y2 + 1] += v;
    }

    public static void build() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
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
            in.nextToken();
            q = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    in.nextToken();
                    add(i, j, i, j, (int) in.nval);
                }
            }
            for (int i = 0, x1, y1, x2, y2, v; i < q; i++) {
                in.nextToken();
                x1 = (int) in.nval;
                in.nextToken();
                y1 = (int) in.nval;
                in.nextToken();
                x2 = (int) in.nval;
                in.nextToken();
                y2 = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                add(x1, y1, x2, y2, v);
            }
            build();
            for (int i = 1; i <= n; i++) {
                out.print(diff[i][1]);
                for (int j = 2; j <= m; j++) {
                    out.print(" " + diff[i][j]);
                }
                out.println();
            }
            clear();
        }
        out.flush();
        out.close();
        br.close();
    }
}
