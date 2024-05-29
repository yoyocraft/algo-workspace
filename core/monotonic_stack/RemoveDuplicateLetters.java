package core.monotonic_stack;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {

    public static final int MAXN = 26;

    public static final char[] st = new char[MAXN];
    public static int top;

    public static int[] cnts = new int[MAXN];
    public static boolean[] enter = new boolean[MAXN];

    public static void init() {
        top = -1;
        Arrays.fill(cnts, 0);
        Arrays.fill(enter, false);
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public String removeDuplicateLetters(String str) {
        init();
        char[] s = str.toCharArray();
        for (char c : s) {
            cnts[c - 'a']++;
        }

        for (char c : s) {
            if (!enter[c - 'a']) {
                // 开始字符尽量小，同时尽量保证不丢失字符
                while (!isEmpty() && st[top] > c && cnts[st[top] - 'a'] > 0) {
                    enter[st[top--] - 'a'] = false;
                }
                st[++top] = c;
                enter[c - 'a'] = true;
            }
            cnts[c - 'a']--;
        }

        return String.valueOf(st, 0, top + 1);
    }

}
