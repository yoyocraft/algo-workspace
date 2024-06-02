package core.unionfind;

/**
 * https://leetcode.cn/problems/similar-string-groups/
 */
public class SimilarStringGroups {
    public static int MAXN = 301;
    public static int[] father = new int[MAXN];
    public static int sets;

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        sets = n;
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        father[find(x)] = find(y);
        sets--;
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        build(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSameSet(i, j)) {
                    continue;
                }
                int diff = diff(strs[i], strs[j]);
                if (diff == 0 || diff == 2) {
                    union(i, j);
                }
            }
        }
        return sets;
    }

    public static int diff(String s, String t) {
        int diff = 0;
        for (int i = 0; i < s.length() && diff < 3; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}
