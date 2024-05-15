package ds.tire;

import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/c552d3b4dfda49ccb883a6371d9a6932
 */
public class Prac1 {

    public static final int MAXN = 2_000_001;

    public static int[][] tree = new int[MAXN][12]; // 12 条路, 0~9, -, #
    public static int[] pass = new int[MAXN];
    public static int cnt;

    public static void build() {
        cnt = 1;
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
        }
    }

    public static int path(char c) {
        if (c == '#') {
            return 10;
        } else if (c == '-') {
            return 11;
        } else {
            return c - '0';
        }
    }

    public static void insert(String word) {
        int p = 1;
        pass[p]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = path(word.charAt(i));
            if (tree[p][path] == 0) {
                tree[p][path] = ++cnt;
            }
            p = tree[p][path];
            pass[p]++;
        }
    }

    public static int count(String pre) {
        int p = 1;
        for (int i = 0, path; i < pre.length(); i++) {
            path = path(pre.charAt(i));
            if (tree[p][path] == 0) {
                return 0;
            }
            p = tree[p][path];
        }
        return pass[p];
    }

    public int[] countConsistentKeys(int[][] b, int[][] a) {
        build();
        StringBuilder builder = new StringBuilder();
        for (int[] nums : a) {
            // a 的特征
            // [1,2,3,4,5] => 1#1#1#1#
            builder.setLength(0);
            for (int i = 1; i < nums.length; i++) {
                builder.append(String.valueOf(nums[i] - nums[i - 1]) + "#");
            }
            insert(builder.toString());
        }

        int[] ans = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            builder.setLength(0);
            int[] nums = b[i];
            for (int j = 1; j < nums.length; j++) {
                builder.append(String.valueOf(nums[j] - nums[j - 1]) + "#");
            }
            ans[i] = count(builder.toString());
        }
        return ans;
    }
}
