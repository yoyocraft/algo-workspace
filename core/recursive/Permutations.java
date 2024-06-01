package core.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        f(nums, 0, ans);
        return ans;
    }

    public static void f(int[] nums, int i, List<List<Integer>> ans) {
        if (i == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            ans.add(list);
            return;
        }
        for (int j = i; j < nums.length; j++) {
            // 每次将 j 位置的数换到 i 位置
            swap(nums, i, j);
            f(nums, i + 1, ans); // 下一轮从 i+1 开始
            swap(nums, i, j);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
