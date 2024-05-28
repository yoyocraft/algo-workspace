package core.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.cn/problems/stickers-to-spell-word
 */
public class StickersToSpellWord {

    public static final int MAXN = 401;
    public static String[] que = new String[MAXN];
    public static int l, r;
    public static List<List<String>> g = new ArrayList<>();
    static {
        for (int i = 0; i < 26; i++) {
            g.add(new ArrayList<>());
        }
    }

    public static HashSet<String> vis = new HashSet<>();

    public static void init() {
        for (int i = 0; i < 26; i++) {
            g.get(i).clear();
        }
        vis.clear();
    }

    public static String sort(String s) {
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        return new String(chs);
    }

    public static String next(String t, String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, j = 0; i < t.length();) {
            if (j == s.length()) {
                builder.append(t.charAt(i++));
            } else {
                if (t.charAt(i) < s.charAt(j)) {
                    builder.append(t.charAt(i++));
                } else if (t.charAt(i) > s.charAt(j)) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return builder.toString();
    }

    public int minStickers(String[] stickers, String target) {
        init();
        for (String s : stickers) {
            s = sort(s);
            for (int i = 0; i < s.length(); i++) {
                if (i == 0 || s.charAt(i) != s.charAt(i - 1)) {
                    g.get(s.charAt(i) - 'a').add(s);
                }
            }
        }

        target = sort(target);
        vis.add(target);
        l = r = 0;
        que[r++] = target;
        int level = 1;
        while (l < r) {
            int size = r - l;
            for (int i = 0; i < size; i++) {
                String cur = que[l++];
                for (String s : g.get(cur.charAt(0) - 'a')) {
                    String next = next(cur, s);
                    if ("".equals(next)) {
                        return level;
                    } else if (!vis.contains(next)) {
                        que[r++] = next;
                        vis.add(next);
                    }
                }
            }
            level++;
        }
        return -1;
    }

}
