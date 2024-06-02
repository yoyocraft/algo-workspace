package core.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/e7ed657974934a30b2010046536a5372
 */
public class UnionFindNowCoder {

    public static final int MAXN = 1_000_001;
    public static int[] father = new int[MAXN];
    public static int[] size = new int[MAXN];
    public static int[] stack = new int[MAXN]; // 用于路径压缩

    public static int n;

    public static void build() {
        for (int i = 0; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    public static int find(int i) {
        // 收集沿路的节点
        int size = 0;
        while (i != father[i]) {
            stack[size++] = i;
            i = father[i];
        }

        // 路径扁平化
        while (size > 0) {
            father[stack[--size]] = i;
        }
        return i;
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);

        if (fx == fy) {
            return;
        }

        // 小挂大
        if (size[fx] >= size[fy]) {
            size[fx] += size[fy];
            father[fy] = fx;
        } else {
            size[fy] += size[fx];
            father[fx] = fy;
        }
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
                    // 判断 x,y 是否在一个集合
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                } else {
                    // 合并 x,y
                    union(x, y);
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
