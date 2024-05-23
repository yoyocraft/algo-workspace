package core.greedy;

/**
 * 「分成k份的最大乘积」
 * 一个数字n一定要分成k份，得到的乘积尽量大是多少
 * 数字n和k，可能非常大，到达10^12规模
 * 结果可能更大，所以返回结果对1000000007取模
 */
public class MaximumProduct {

    public static int maxValue1(int n, int k) {
        return f1(n, k);
    }

    public static int f1(int rest, int k) {
        if (k == 1) {
            return rest;
        }

        int ans = Integer.MIN_VALUE;
        // 枚举切割数 cur
        for (int cur = 1; cur <= rest && (rest - cur) >= (k - 1); cur++) {
            int curAns = cur * f1(rest - cur, k - 1);
            ans = Math.max(ans, curAns);
        }
        return ans;
    }

    public static int maxValue2(int n, int k) {
        int mod = (int) 1e9 + 7;
        long a = n / k; // 尽量均分
        int b = n % k;
        long part1 = power(a + 1, b, mod); // b 组需要多一个
        long part2 = power(a, k - b, mod); // k-b 使用 a
        return (int) ((part1 * part2) % mod);
    }

    public static long power(long x, int n, int mod) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (ans * x) % mod;
            }
            x = (x * x) % mod;
            n >>>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 30;
        int testTimes = 2000;
        System.out.println("测试开始!");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int k = (int) (Math.random() * n) + 1;
            int ans1 = maxValue1(n, k);
            int ans2 = maxValue2(n, k);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }

            if (i % 100 == 0) {
                System.err.println("测试轮次： " + i);
            }
        }
        System.out.println("测试结束");
    }

}
