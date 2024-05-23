package core.greedy;

import java.util.HashMap;

public class MinimumNumberEatOranges {
    /**
     * https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/
     */
    class Solution {

        public static HashMap<Integer, Integer> memo = new HashMap<>();

        public int minDays(int n) {
            if (n <= 1)
                return n;

            if (memo.containsKey(n)) {
                return memo.get(n);
            }

            int ans = Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3));
            memo.put(n, ans);
            return ans;
        }
    }
}
