package tips.scales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/d88ef50f8dab4850be8cd4b95514bbbd
 * 
 * tips: 数据量只有 10 => 全排列可以通过
 */
public class KillMonsterEverySkillUseOnce {

    public static final int MAXN = 11;

    public static final int[] damage = new int[MAXN];
    public static final int[] threshold = new int[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int t = (int) in.nval;
            for (int k = 0, n, m; k < t; k++) {
                in.nextToken();
                n = (int) in.nval;
                in.nextToken();
                m = (int) in.nval;
                for (int i = 0; i < n; i++) {
                    in.nextToken();
                    damage[i] = (int) in.nval;
                    in.nextToken();
                    threshold[i] = (int) in.nval;
                }
                int ans = f(n, 0, m);
                out.println(ans == Integer.MAX_VALUE ? -1 : ans);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int f(int n, int i, int restBlood) {
        if (restBlood <= 0) {
            // 上一轮就结束了战斗，返回使用的技能数
            return i;
        }
        // 技能都用完了
        if (i == n) {
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            swap(i, j);
            ans = Math.min(ans, f(n, i + 1, restBlood - (restBlood <= threshold[i] ? damage[i] * 2 : damage[i])));
            swap(i, j);
        }
        return ans;
    }

    public static void swap(int i, int j) {
        int tmp = damage[i];
        damage[i] = damage[j];
        damage[j] = tmp;
        tmp = threshold[i];
        threshold[i] = threshold[j];
        threshold[j] = tmp;
    }
}
