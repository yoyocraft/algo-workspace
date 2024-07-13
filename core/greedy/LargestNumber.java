package core.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/largest-number/
 */
public class LargestNumber {

    public static void main(String[] args) {
        LargestNumber.test(8, 5, 4, 2000);
    }

    // region solve1
    public static String solve1(String[] strs) {
        List<String> ans = new ArrayList<>();
        f(strs, 0, ans);
        ans.sort((a, b) -> a.compareTo(b));
        return ans.get(0);
    }

    public static void f(String[] strs, int i, List<String> ans) {
        if (i == strs.length) {
            StringBuilder path = new StringBuilder();
            for (String s : strs) {
                path.append(s);
            }
            ans.add(path.toString());
        } else {
            for (int j = i; j < strs.length; j++) {
                swap(strs, i, j);
                f(strs, i + 1, ans);
                swap(strs, i, j);
            }
        }
    }

    public static void swap(String[] strs, int i, int j) {
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }

    // endregion

    // region solve2
    public static String solve2(String[] strs) {
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            path.append(strs[i]);
        }
        return path.toString();
    }
    // endregion

    // region test

    /**
     * @param n     max number of strings
     * @param m     max length of string
     * @param v     number of character types
     * @param times test turn times
     */
    public static void test(int n, int m, int v, int times) {
        System.out.println("Starting Test!");
        for (int i = 1; i <= times; i++) {
            String[] strs = randomStringArray(n, m, v);
            String ans1 = solve1(strs);
            String ans2 = solve2(strs);
            if (!ans1.equals(ans2)) {
                System.err.println("Error Answer!");
            }
            if (i % 100 == 0) {
                System.out.println("Testing on " + i + " times");
            }
        }
        System.out.println("Ending Test!");
    }

    public static String[] randomStringArray(int n, int m, int v) {
        String[] ans = new String[(int) (Math.random() * n) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = randomString(m, v);
        }
        return ans;
    }

    public static String randomString(int m, int v) {
        int len = (int) (Math.random() * m) + 1;
        char[] ans = new char[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (char) ('a' + (int) (Math.random() + v));
        }
        return String.valueOf(ans);
    }
    // endregion

    // region leetcode
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        if ("0".equals(strs[0])) {
            return "0";
        }
        StringBuilder path = new StringBuilder();
        for (String s : strs) {
            path.append(s);
        }
        return path.toString();
    }

    // endregion

}
