package core.slide_window;

public class ReplaceTheSubstringForBalancedString {
    // nothing

    /**
     * https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
     */
    class Solution {
        // 寻找最小的窗口可以使得字符串合法
        public int balancedString(String str) {
            int n = str.length();
            int[] arr = new int[n];
            int[] cnts = new int[4];
            for (int i = 0; i < n; i++) {
                char c = str.charAt(i);
                arr[i] = parse(c);
                cnts[arr[i]]++; // 统计每一种字符出现的频次
            }
            int require = n / 4;
            int ans = n; // 至少需要修改多长的子串，才可以做到四种字符一样多
            for (int l = 0, r = 0; l < n; l++) {
                while (!ok(cnts, r - l, require) && r < n) {
                    // 扩展自由窗口
                    cnts[arr[r++]]--;
                }
                if (ok(cnts, r - l, require)) {
                    // [l, r) 作为自由窗口可以做到字符串合法
                    ans = Math.min(ans, r - l);
                }
                // 做不到，l++
                cnts[arr[l]]++;
            }
            return ans;
        }

        /**
         * l...r 范围上的字符为自由变化窗口
         * 
         * @param cnts    每一种字符的词频
         * @param len     自由变化窗口的长度
         * @param require 每一种字符需要的数量
         * @return 能否利用 len 长度的自由窗口使得自由窗口外的字符达到 require
         */
        public static boolean ok(int[] cnts, int len, int require) {
            for (int i = 0; i < 4; i++) {
                if (cnts[i] > require) {
                    return false;
                }
                len -= require - cnts[i];
            }
            return len == 0;
        }

        public static int parse(char c) {
            switch (c) {
                case 'Q':
                    return 0;
                case 'W':
                    return 1;
                case 'E':
                    return 2;
                case 'R':
                    return 3;
            }
            return -1;
        }
    }

}