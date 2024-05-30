package core.monotonic_stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.nowcoder.com/practice/77199defc4b74b24b8ebf6244e1793de
 * 
 * https://leetcode.cn/problems/steps-to-make-array-non-decreasing/
 */
public class BigFishEatSmallFish {

    public static final int MAXN = 100_001;
    public static final int[] arr = new int[MAXN];
    public static int n;

    public static final int[][] st = new int[MAXN][2];
    public static int top;

    public static void init() {
        top = -1;
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.println(solve());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int solve() {
        init();
        int ans = 0;
        for (int i = n - 1, turn; i >= 0; i--) {
            turn = 0; // 当前 i 位置吃完右侧可以吃的鱼需要的轮次
            while (!isEmpty() && st[top][0] < arr[i]) {
                turn = Math.max(turn + 1, st[top--][1]);
            }
            st[++top][0] = arr[i];
            st[top][1] = turn;
            ans = Math.max(ans, turn);
        }
        return ans;
    }

    // region leetcode

    public int totalSteps(int[] nums) {
        init();
        int ans = 0;
        for (int i = nums.length - 1, turn; i >= 0; i--) {
            turn = 0;
            while (!isEmpty() && st[top][0] < nums[i]) {
                turn = Math.max(turn + 1, st[top--][1]);
            }
            st[++top][0] = nums[i];
            st[top][1] = turn;
            ans = Math.max(ans, turn);
        }
        return ans;
    }

}
