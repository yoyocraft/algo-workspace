package core.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * https://www.luogu.com.cn/problem/P3366
 */
public class Kruskal {

    public static int MAXN = 5_001;

    public static int MAXM = 200_001;

    public static int[] father = new int[MAXN];

    public static int[][] edges = new int[MAXM][3];

    public static int n, m;

    public static void build() {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static boolean union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
            Arrays.sort(edges, 0, m, (a, b) -> Integer.compare(a[2], b[2]));
            int ans = 0, edgeCnt = 0;
            for (int[] e : edges) {
                if (union(e[0], e[1])) {
                    edgeCnt++;
                    ans += e[2];
                }
            }
            out.println(edgeCnt == n - 1 ? ans : "orz");
        }

        out.flush();
        out.close();
        br.close();
    }

}
