package core.kmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * https://www.luogu.com.cn/problem/P4391
 */
public class RepeatMinimumLength {

    public static int MAXN = 1_000_001;
    public static int[] next = new int[MAXN];
    public static int n;
    public static char[] s;

    public static void nextArray() {
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i <= n) {
            if (s[i - 1] == s[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
    }

    public static int compute() {
        nextArray();
        return n - next[n];
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        // BufferedReader in = new BufferedReader(new FileReader("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(in.readLine());
        s = in.readLine().toCharArray();
        out.println(compute());
        out.flush();
        out.close();
        in.close();
    }
}