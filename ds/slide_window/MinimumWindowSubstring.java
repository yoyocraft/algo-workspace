package ds.slide_window;

public class MinimumWindowSubstring {
    // nothing

    /**
     * https://leetcode.cn/problems/minimum-window-substring/
     */
    class Solution {
        public String minWindow(String str, String tar) {
            // 不可能的情况
            if (str.length() < tar.length()) {
                return "";
            }
            char[] s = str.toCharArray(), t = tar.toCharArray();
            // 记录需要哪些字符
            int[] cnts = new int[256];
            for (char c : t) {
                cnts[c]--;
            }

            int len = Integer.MAX_VALUE;
            int start = 0;
            for (int l = 0, r = 0, debt = t.length; r < s.length; r++) {
                // 合法字符
                if (cnts[s[r]]++ < 0) {
                    debt--;
                }
                // r 这个位置结尾，可以覆盖 tar, 开始收缩窗口
                if (debt == 0) {
                    while (cnts[s[l]] > 0) {
                        cnts[s[l++]]--;
                    }
                    // l...r 就是以 r 位置结尾的最小覆盖子串
                    if (r - l + 1 < len) {
                        len = r - l + 1;
                        start = l;
                    }
                }
            }
            return len == Integer.MAX_VALUE ? "" : str.substring(start, start + len);
        }
    }

}