package core.prime_number;

/**
 * https://leetcode.cn/problems/count-primes/
 */
public class EhrlichAndEuler {
    public int countPrimes(int n) {
        return enhlich(n - 1);
    }

    /**
     * 埃氏筛
     */
    public static int enhlich(int n) {
        boolean[] np = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!np[i]) {
                for (int j = i * i; j <= n; j += i) {
                    np[j] = true;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!np[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 欧拉筛
     */
    public static int euler(int n) {
        boolean[] np = new boolean[n + 1];
        // prime 收集所有的质数，收集的个数是 cnt
        int[] prime = new int[n / 2 + 1];
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!np[i]) {
                prime[cnt++] = i;
            }
            for (int j = 0; j < cnt; j++) {
                if (i * prime[j] > n) {
                    break;
                }
                np[i * prime[j]] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        return cnt;
    }

    /**
     * 计数版埃氏筛
     */
    public static int enhlichForCnt(int n) {
        if (n <= 1) {
            return 0;
        }

        boolean[] np = new boolean[n + 1];
        int cnt = (n + 1) / 2;
        for (int i = 3; i * i <= n; i += 2) {
            if (!np[i]) {
                for (int j = i * i; j <= n; j += 2 * i) {
                    if (!np[j]) {
                        cnt--;
                        np[j] = true;
                    }
                }
            }
        }
        return cnt;
    }

}
