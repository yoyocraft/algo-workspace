package core.tire;

import java.util.HashSet;

/**
 * https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class Prac2 {
    // nothing...

    class Solution {

        public static final int MAXN = 3_000_001;

        public static int[][] tree = new int[MAXN][2];
        public static int cnt;
        public static int high;

        public static void build(int[] nums) {
            cnt = 1;
            // find max num
            int mx = 0;
            for (int x : nums) {
                mx = Math.max(mx, x);
            }
            // 忽略前缀0
            high = 31 - Integer.numberOfLeadingZeros(mx);
            for (int x : nums) {
                insert(x);
            }
        }

        public static void clear() {
            for (int i = 1; i <= cnt; i++) {
                tree[i][0] = tree[i][1] = 0;
            }
        }

        public static void insert(int num) {
            int p = 1;
            for (int i = high, path; i >= 0; i--) {
                path = (num >> i) & 1;
                if (tree[p][path] == 0) {
                    tree[p][path] = ++cnt;
                }
                p = tree[p][path];
            }
        }

        public static int maxXor(int num) {
            int ans = 0;
            int p = 1;
            for (int i = high, status, want; i >= 0; i--) {
                status = (num >> i) & 1; // 第 i 位的状态
                want = status ^ 1; // num 第 i 位希望遇到的状态
                if (tree[p][want] == 0) { // 判断是否可以达成
                    want ^= 1; // 不能达成，恢复
                }
                ans |= (status ^ want) << i; // 状态更新
                p = tree[p][want];
            }
            return ans;
        }

        public int findMaximumXOR(int[] nums) {
            // return findMaximumXorWithTrie(nums);
            return findMaximumXorWithHashTable(nums);
        }

        public int findMaximumXorWithTrie(int[] nums) {
            build(nums);
            int ans = 0;
            for (int x : nums) {
                ans = Math.max(ans, maxXor(x));
            }
            clear();
            return ans;
        }

        public int findMaximumXorWithHashTable(int[] nums) {
            int mx = 0;
            for (int x : nums) {
                mx = Math.max(mx, x);
            }

            int ans = 0;
            HashSet<Integer> set = new HashSet<>();
            int high = 31 - Integer.numberOfLeadingZeros(mx);
            for (int i = high; i >= 0; i--) {
                int better = ans | (1 << i);
                set.clear();
                for (int x : nums) {
                    x = (x >> i) << i; // 只保留 [i, 31] 上的状态
                    set.add(x);
                    // num ^ 某状态 是否能 达成better目标，就在set中找 某状态 : better ^ num
                    if (set.contains(better ^ x)) {
                        ans = better;
                        break;
                    }
                }
            }

            return ans;
        }

    }

}