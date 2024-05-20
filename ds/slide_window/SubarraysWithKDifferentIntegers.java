package ds.slide_window;

import java.util.Arrays;

public class SubarraysWithKDifferentIntegers {
    // nothing

    /**
     * https://leetcode.cn/problems/subarrays-with-k-different-integers/
     */
    class Solution {

        public static final int MAXN = 20_001;
        public static final int[] cnts = new int[MAXN];

        /**
         * 统计 arr 中数字种类不超过 k 的子数组个数
         */
        public static int numOfMostKinds(int[] arr, int k) {
            Arrays.fill(cnts, 1, arr.length + 1, 0);
            int ans = 0;
            for (int l = 0, r = 0, collect = 0; r < arr.length; r++) {
                if (++cnts[arr[r]] == 1) {
                    collect++;
                }
                while (collect > k) {
                    if (--cnts[arr[l++]] == 0) {
                        collect--;
                    }
                }
                // [l, r] 之间都不超过 k
                ans += r - l + 1;
            }
            return ans;
        }

        public int subarraysWithKDistinct(int[] nums, int k) {
            // 统计 <=k 和 <=(k-1) 的，二者相减
            return numOfMostKinds(nums, k) - numOfMostKinds(nums, k - 1);
        }
    }
}