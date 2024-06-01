package core.recursive;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a
 */
public class Subsequences {

    public String[] generatePermutation(String str) {
        char[] s = str.toCharArray();
        Set<String> set = new HashSet<>();
        f(s, 0, new char[s.length], 0, set);
        String[] res = new String[set.size()];
        int i = 0;
        for (String cur : set) {
            res[i++] = cur;
        }
        return res;
    }

    /**
     * @param size path 中填到了第几个字符
     */
    public static void f(char[] s, int i, char[] path, int size, Set<String> set) {
        if (i == s.length) {
            set.add(String.valueOf(path, 0, size));
        } else {
            path[size] = s[i];
            f(s, i + 1, path, size + 1, set);
            f(s, i + 1, path, size, set);
        }
    }

}
