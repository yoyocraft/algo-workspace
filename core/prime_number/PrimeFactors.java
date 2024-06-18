package core.prime_number;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/largest-component-size-by-common-factor/
 */
public class PrimeFactors {

    public static final int MAXV = 100_001;
    // 记录每个数的最早质因子
    // e.g. factor[a] = b => 数字 b 拥有最早的质因子是 a
    public static final int[] factors = new int[MAXV];

    public static final int MAXN = 20_001;
    public static final int[] father = new int[MAXN];
    public static final int[] size = new int[MAXN];
    public static int n;

    public static void init() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
        Arrays.fill(factors, -1);
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            size[fy] += size[fx];
        }
    }

    public static int getMaxSize() {
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            maxSize = Math.max(maxSize, size[i]);
        }
        return maxSize;
    }

    public int largestComponentSize(int[] nums) {
        n = nums.length;
        init();
        for (int i = 0, x; i < n; i++) {
            x = nums[i];
            for (int j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    if (factors[j] == -1) {
                        factors[j] = i;
                    } else {
                        union(factors[j], i);
                    }
                    while (x % j == 0) {
                        x /= j;
                    }
                }
            }
            if (x > 1) {
                if (factors[x] == -1) {
                    factors[x] = i;
                } else {
                    union(factors[x], i);
                }
            }
        }
        return getMaxSize();
    }

    // region show all factors test
    public static void main(String[] args) {
        showAllFactors(12);
    }

    public static void showAllFactors(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                System.out.println(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            System.out.println(n);
        }
    }
}
