package core.recursive_nested;

/**
 * https://leetcode.cn/problems/decode-string/
 */
public class DecodeString {

    /**
     * 记录当前处理到的位置
     */
    public static int idx;

    public static String f(char[] s, int i) {
        StringBuilder builder = new StringBuilder();
        int cnt = 0;
        while (i < s.length && s[i] != ']') {
            if (Character.isAlphabetic(s[i])) {
                builder.append(s[i++]);
            } else if (Character.isDigit(s[i])) {
                cnt = cnt * 10 + s[i++] - '0';
            } else {
                // 遇到了 '['
                builder.append(handle(f(s, i + 1), cnt));
                i = idx + 1; // 跳过 ], 继续处理
                cnt = 0;
            }
        }
        idx = i;
        return builder.toString();
    }

    public static String handle(String s, int cnt) {
        return s.repeat(cnt);
    }

    public String decodeString(String s) {
        idx = 0;
        return f(s.toCharArray(), 0);
    }
}