package core.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 */
public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        f(nums, 0, path, ans);
        return ans;
    }

    public static void f(int[] nums, int i, List<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));
        if (i == nums.length) {
            return;
        }

        for (int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            f(nums, j + 1, path, ans);
            path.remove(path.size() - 1);
        }
    }
}
