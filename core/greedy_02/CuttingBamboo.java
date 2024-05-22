package core.greedy_02;

public class CuttingBamboo {
    /**
     * https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
     */
    class Solution {

        /**
         * @return (x ** n) % mod
         */
        public static long pow(long x, int n, int mod) {
            long ans = 1L;
            while (n > 0) {
                if ((n & 1) == 1) {
                    ans = (ans * x) % mod;
                }
                n >>>= 1;
                x = (x * x) % mod;
            }
            return ans;
        }

        public int cuttingBamboo(int n) {
            if (n == 2) {
                return 1;
            }
            if (n == 3) {
                return 2;
            }

            final int MOD = (int) 1e9 + 7;

            int tail = n % 3 == 0 ? 1 : (n % 3 == 1 ? 4 : 2);
            int power = (tail == 1 ? n / 3 : (n - tail) / 3);
            return (int) pow(3, power, MOD) * tail % MOD;
        }
    }
}
