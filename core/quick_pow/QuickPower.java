package core.quick_pow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.luogu.com.cn/problem/P1226
 */
public class QuickPower {

    public static final String PATTERN = "%s^%s mod %s=%s";

    public static long a, b, p;

    // return a^b mod p
    public static int power() {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * a) % p;
            }
            a = (a * a) % p;
            b >>>= 1;
        }
        return (int) ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        a = (int) in.nval;
        in.nextToken();
        b = (int) in.nval;
        in.nextToken();
        p = (int) in.nval;
        out.println(String.format(PATTERN, a, b, p, power()));
        out.flush();
        out.close();
        br.close();
    }
}
