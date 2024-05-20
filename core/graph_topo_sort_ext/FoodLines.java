package core.graph_topo_sort_ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * https://www.luogu.com.cn/problem/P4017
 */
public class FoodLines {

    public static final int MAXN = 5_001;
    public static final int MAXM = 500_001;
    public static final int MOD = 80112002;

    public static int[] head = new int[MAXN];
    public static int[] next = new int[MAXM];
    public static int[] to = new int[MAXM];
    public static int cnt;

    public static int[] que = new int[MAXN];
    public static int[] indgree = new int[MAXN];
    public static int[] lines = new int[MAXN];
    public static int n, m, l, r;

    public static void build(int n) {
        cnt = 1;
        Arrays.fill(indgree, 0, n + 1, 0);
        Arrays.fill(lines, 0, n + 1, 0);
        Arrays.fill(head, 0, n + 1, 0);
    }

    public static void addEdge(int u, int v) {
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
    }

    public static int ways() {
        l = r = 0;
        for (int i = 1; i <= n; i++) {
            if (indgree[i] == 0) {
                que[r++] = i;
                lines[i] = 1;
            }
        }
        int ans = 0;
        while (l < r) {
            int u = que[l++];
            if (head[u] == 0) {
                // u 节点不再有后续邻居节点
                ans = (ans + lines[u]) % MOD;
            } else {
                for (int ei = head[u], v; ei > 0; ei = next[ei]) {
                    v = to[ei];
                    lines[v] = (lines[u] + lines[v]) % MOD;
                    if (--indgree[v] == 0) {
                        que[r++] = v;
                    }
                }
            }
        }
        return ans;
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
            build(n);
            for (int i = 0, u, v; i < m; i++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                addEdge(u, v);
                indgree[v]++;
            }
            out.println(ways());
        }
        out.flush();
        out.close();
        br.close();
    }

}
