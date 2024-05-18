package ds.slide_window;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters {
    // nothing
}

class Solution {
    public int lengthOfLongestSubstring(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] last = new int[256]; // 记录每一种字符上一次出现的位置
        Arrays.fill(last, -1);
        int ans = 0;
        for (int l = 0, r = 0; r < n; r++) {
            l = Math.max(l, last[s[r]] + 1); // 更新 l
            ans = Math.max(ans, r - l + 1);
            last[s[r]] = r; // 更新当前字符上一次出现的位置
        }
        return ans;
    }
}