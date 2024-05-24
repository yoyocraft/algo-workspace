package core.kmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * https://www.luogu.com.cn/problem/P4824
 */
public class DeleteAgainAndAgain {

    public static int MAXN = 1_000_001;
    public static char[] s1, s2;
    public static int[] next = new int[MAXN];
    public static int[] stack1 = new int[MAXN];
    public static int[] stack2 = new int[MAXN];
    public static int size;

    public static void nextArray(int n) {
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i < n) {
            if (s2[i - 1] == s2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
    }

    public static void compute() {
        size = 0;
        int n = s1.length, m = s2.length, x = 0, y = 0;
        nextArray(m);
        while (x < n) {
            if (s1[x] == s2[y]) {
                stack1[size] = x;
                stack2[size] = y;
                size++;
                x++;
                y++;
            } else if (y == 0) {
                stack1[size] = x;
                stack2[size] = -1;
                size++;
                x++;
            } else {
                y = next[y];
            }

            if (y == m) {
                size -= m;
                y = size > 0 ? stack2[size - 1] + 1 : 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        s1 = in.readLine().toCharArray();
        s2 = in.readLine().toCharArray();
        compute();
        for (int i = 0; i < size; i++) {
            out.print(s1[stack1[i]]);
        }
        out.flush();
        out.close();
        in.close();
    }
}
