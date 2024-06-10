package core.double_pointer;

/**
 * https://leetcode.cn/problems/first-missing-positive/
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        // [0, l] => nums[i] == i + 1
        int l = 0;
        // [r, n) => 垃圾区域
        // 最好的状况下，认为1~r是可以收集全的，每个数字收集1个，不能有垃圾
        // 有垃圾呢？预期就会变差(r--)
        int r = nums.length;

        while (l < r) {
            if (nums[l] == l + 1) {
                l++;
            } else if (nums[l] <= l || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
                // 交换到垃圾区
                swap(nums, l, --r);
            } else {
                // 交换到合适的位置
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
