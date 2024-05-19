package ds.graph_topo_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.nowcoder.com/practice/88f7e156ca7d43a1a535f619cd3f495c
 */
public class TopoSortDynamicNowcoder {

    public static final int MAXN = 200_001;

    public static int[] que = new int[MAXN];
    public static int l, r;
    public static int[] indgree = new int[MAXN];

    public static int[] ans = new int[MAXN];
    public static int n, m;

    public static boolean topoSort(List<List<Integer>> g) {
        l = r = 0;
        for (int i = 1; i <= n; i++) {
            if (indgree[i] == 0) {
                que[r++] = i;
            }
        }
        int idx = 0;
        while (l < r) {
            int cur = que[l++];
            ans[idx++] = cur;
            for (int next : g.get(cur)) {
                if (--indgree[next] == 0) {
                    que[r++] = next;
                }
            }
        }
        return idx == n;
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
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                g.add(new ArrayList<>());
            }
            Arrays.fill(indgree, 0, n + 1, 0);
            for (int i = 0, from, to; i < m; i++) {
                in.nextToken();
                from = (int) in.nval;
                in.nextToken();
                to = (int) in.nval;
                g.get(from).add(to);
                indgree[to]++;
            }
            if (!topoSort(g)) {
                out.println(-1);
            } else {
                for (int i = 0; i < n - 1; i++) {
                    out.print(ans[i] + " ");
                }
                out.println(ans[n - 1]);
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
