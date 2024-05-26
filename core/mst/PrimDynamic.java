package core.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.luogu.com.cn/problem/P3366
 */
public class PrimDynamic {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            List<List<int[]>> graph = new ArrayList<>();
            int n = (int) in.nval;
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0, u, v, w; i < m; i++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                w = (int) in.nval;
                graph.get(u).add(new int[] { v, w });
                graph.get(v).add(new int[] { u, w });
            }

            // 0 - 到达的节点
            // 1 - 到达的花费
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            for (int[] e : graph.get(1)) {
                heap.add(e);
            }

            boolean[] vis = new boolean[n + 1];
            int nodeCnt = 1;
            vis[1] = true;
            int ans = 0;
            while (!heap.isEmpty()) {
                int[] edge = heap.poll();
                int next = edge[0], cost = edge[1];
                if (!vis[next]) {
                    nodeCnt++;
                    vis[next] = true;
                    ans += cost;
                    for (int[] e : graph.get(next)) {
                        heap.add(e);
                    }
                }
            }

            out.println(nodeCnt == n ? ans : "orz");
        }

        out.flush();
        out.close();
        br.close();
    }
}
