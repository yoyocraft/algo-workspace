package core.dp_one_dimen;

/**
 * https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 */
public class UniqueSubstringsWraparoundString {

    public int findSubstringInWraproundString(String str) {
        int n = str.length();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = str.charAt(i) - 'a';
        }
        // dp[i]: str 中必须以 'a'+i 的子串，最长延伸长度是多少，延伸一定要根据 base 串的规则
        int[] dp = new int[26];
        dp[s[0]] = 1;
        for (int i = 1, cur, pre, len = 1; i < n; i++) {
            cur = s[i];
            pre = s[i - 1];
            // 前一个字符是 'z' && 当前字符是 'a' || 前一个字符比当前字符的 ASCII 码少 1
            if ((pre == 25 && cur == 0) || pre + 1 == cur) {
                len++;
            } else {
                len = 1;
            }
            dp[cur] = Math.max(dp[cur], len);
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += dp[i];
        }
        return ans;
    }
}
