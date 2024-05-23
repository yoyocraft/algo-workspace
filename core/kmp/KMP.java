package core.kmp;

public class KMP {
    /**
     * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string
     */
    class Solution {
        public int strStr(String s, String t) {
            return kmp(s.toCharArray(), t.toCharArray());
        }

        public static int kmp(char[] s, char[] t) {
            int n = s.length, m = t.length, x = 0, y = 0;
            int[] next = nextArray(t, m);

            while (x < n && y < m) {
                if (s[x] == t[y]) {
                    x++;
                    y++;
                } else if (y > 0) {
                    y = next[y];
                } else {
                    x++;
                }
            }
            return y == m ? x - y : -1;
        }

        public static int[] nextArray(char[] p, int n) {
            if (n == 1) {
                return new int[] { -1 };
            }
            int[] next = new int[n];
            next[0] = -1;
            next[1] = 0;
            int i = 2, cn = 0;
            while (i < n) {
                if (p[i - 1] == p[cn]) {
                    next[i++] = ++cn;
                } else if (cn > 0) {
                    cn = next[cn];
                } else {
                    next[i++] = 0;
                }
            }
            return next;
        }
    }
}
