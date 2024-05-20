package core.slide_window;

import java.util.Arrays;

public class LongestSubstringWithAtLeastKRepeating {
    // nothing

    /**
     * https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/
     */
    class Solution {
        public int longestSubstring(String str, int k) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[] cnts = new int[256];
            int ans = 0;
            // 每次要求子串必须含有 require 种字符，每种字符都必须 >=k 次，这样的最长子串是多长
            for (int require = 1; require <= 26; require++) {
                Arrays.fill(cnts, 0);
                // collect: 窗口中收集的字符种类数
                // satisfy: 窗口中达标的字符种类数
                for (int l = 0, r = 0, collect = 0, satisfy = 0; r < n; r++) {
                    cnts[s[r]]++;
                    if (cnts[s[r]] == 1) {
                        collect++;
                    }
                    if (cnts[s[r]] == k) {
                        satisfy++;
                    }
                    while (collect > require) {
                        if (cnts[s[l]] == 1) {
                            collect--;
                        }
                        if (cnts[s[l]] == k) {
                            satisfy--;
                        }
                        cnts[s[l++]]--;
                    }
                    // [l, r] 合法
                    if (satisfy == require) {
                        ans = Math.max(ans, r - l + 1);
                    }
                }
            }
            return ans;
        }
    }
}