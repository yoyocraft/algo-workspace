package core.tire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prac3 {
    // nothing

    public static final int MAXN = 10_001;
    public static final int[][] tree = new int[MAXN][26];;
    public static final int[] pass = new int[MAXN];
    public static final String[] end = new String[MAXN];
    public static int cnt;

    public static void build(String[] words) {
        cnt = 1;
        for (String word : words) {
            insert(word);
        }
    }

    public static void insert(String word) {
        int p = 1;
        pass[p]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[p][path] == 0) {
                tree[p][path] = ++cnt;
            }
            p = tree[p][path];
            pass[p]++;
        }
        end[p] = word;
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = null;
        }
    }

    public static int dfs(char[][] board, int i, int j, int t, List<String> ans) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == 0) {
            return 0;
        }
        // 不越界 且 不是回头路
        char tmp = board[i][j];

        int road = tmp - 'a';
        t = tree[t][road];
        if (pass[t] == 0) {
            return 0;
        }

        // board[i][j] 有必要来
        int fix = 0; // // 从当前i，j位置出发，一共收集到了几个字符串
        if (end[t] != null) {
            fix++;
            ans.add(end[t]);
            end[t] = null;
        }

        board[i][j] = 0;
        fix += dfs(board, i + 1, j, t, ans);
        fix += dfs(board, i - 1, j, t, ans);
        fix += dfs(board, i, j + 1, t, ans);
        fix += dfs(board, i, j - 1, t, ans);
        pass[t] -= fix;
        board[i][j] = tmp;
        return fix;
    }

    public List<String> findWords(char[][] board, String[] words) {
        build(words);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, 1, ans);
            }
        }
        clear();
        return ans;
    }

}