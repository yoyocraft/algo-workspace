package core.greedy_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://www.nowcoder.com/practice/1ae8d0b6bb4e4bcdbf64ec491f63fc37
 */
public class LineSegmentsOverlap {

    public static final int MAXN = 10_010;
    public static int[][] arr = new int[MAXN][2];
    public static int n;
    public static PriorityQueue<Integer> heap = new PriorityQueue<>();

    public static void build() {
        for (int i = 0; i < n; i++) {
            arr[i][0] = arr[i][1] = 0;
        }
        heap.clear();
    }

    /**
     * 同 https://leetcode.cn/problems/meeting-rooms-ii/
     * 
     * 求最大重合数
     */
    public static int getMinLines() {
        Arrays.sort(arr, 0, n, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (!heap.isEmpty() && heap.peek() <= arr[i][0]) {
                heap.poll();
            }
            heap.add(arr[i][1]);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            build();
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
            }
            out.println(getMinLines());
        }
        out.flush();
        out.close();
        br.close();
    }
}
