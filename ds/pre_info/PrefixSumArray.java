package ds.pre_info;

public class PrefixSumArray {
    // nothing

    /**
     * https://leetcode.cn/problems/range-sum-query-immutable/
     */
    class NumArray {

        public int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return sum[right + 1] - sum[left];
        }
    }

}
