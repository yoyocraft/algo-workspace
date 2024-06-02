package core.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.luogu.com.cn/problem/P3367
 */
public class UnionFindLuogu {

    public static final int MAXN = 10_001;
    public static int[] father = new int[MAXN];
    public static int n;

    public static void build() {
        for (int i = 0; i <= n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void unoin(int x, int y) {
        father[find(x)] = find(y);
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            build();
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0, op, x, y; i < m; i++) {
                in.nextToken();
                op = (int) in.nval;
                in.nextToken();
                x = (int) in.nval;
                in.nextToken();
                y = (int) in.nval;
                if (op == 1) {
                    unoin(x, y);
                } else {
                    out.println(isSameSet(x, y) ? "Y" : "N");
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }

}
