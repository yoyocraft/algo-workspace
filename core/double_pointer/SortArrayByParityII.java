package core.double_pointer;

/**
 * https://leetcode.cn/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityII {

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        for (int even = 0, odd = 1; odd < n && even < n;) {
            if ((nums[n - 1] & 1) == 0) {
                swap(nums, even, n - 1);
                even += 2;
            } else {
                swap(nums, odd, n - 1);
                odd += 2;
            }
        }
        return nums;
    }

}
