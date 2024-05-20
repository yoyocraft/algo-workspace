package core.mod_principle;

public class NthMagicalNumber {

    public static void main(String[] args) {
        Solution solution = new NthMagicalNumber().new Solution();
        int n = 1, a = 2, b = 3;
        int ans = solution.nthMagicalNumber(n, a, b);
        assert ans == 2;
    }

    /**
     * https://leetcode.cn/problems/nth-magical-number/
     */
    class Solution {
        public static final int MOD = (int) 1e9 + 7;

        public int nthMagicalNumber(int n, int a, int b) {
            long lcm = lcm(a, b);
            long l = -1L, r = (long) n * Math.min(a, b), m;
            while (l + 1 < r) {
                m = (l + r) / 2;
                if (valid(m, a, b, lcm, n)) {
                    r = m;
                } else {
                    l = m;
                }
            }
            return (int) (r % MOD);
        }

        public static boolean valid(long m, long a, long b, long lcm, long n) {
            return m / a + m / b - m / lcm >= n;
        }

        public static long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public static long lcm(long a, long b) {
            return (long) a / gcd(a, b) * b;
        }
    }
}
