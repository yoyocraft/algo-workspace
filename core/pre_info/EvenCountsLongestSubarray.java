package core.pre_info;

import java.util.Arrays;

public class EvenCountsLongestSubarray {
    // nothing

    /**
     * https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts/
     */
    class Solution {

        public static int move(char c) {
            switch (c) {
                case 'a':
                    return 0;
                case 'e':
                    return 1;
                case 'i':
                    return 2;
                case 'o':
                    return 3;
                case 'u':
                    return 4;
                default:
                    return -1;
            }
        }

        public int findTheLongestSubstring(String s) {
            int n = s.length();
            int[] map = new int[32];
            Arrays.fill(map, -2);
            map[0] = -1;
            int ans = 0;
            for (int i = 0, status = 0, m; i < n; i++) {
                // 情况1 : 当前字符不是元音，status不变
                // 情况2 : 当前字符是元音，a~u(0~4)，修改相应的状态
                m = move(s.charAt(i));
                if (m != -1) {
                    status ^= 1 << m;
                }
                // 找到和之前一样的状态
                // status: 0....i字符串上，aeiou的奇偶性
                if (map[status] != -2) {
                    ans = Math.max(ans, i - map[status]);
                } else {
                    map[status] = i;
                }
            }

            return ans;
        }
    }

}