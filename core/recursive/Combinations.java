package core.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets-ii/
 */
public class Combinations {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        f(nums, 0, new int[nums.length], 0, ans);
        return ans;
    }

    public static void f(int[] nums, int i, int[] path, int size, List<List<Integer>> ans) {
        if (i == nums.length) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                cur.add(path[j]);
            }
            ans.add(cur);
            return;
        }

        // 相同的数分组
        int j = i + 1;
        while (j < nums.length && nums[i] == nums[j]) {
            j++;
        }

        // 不取当前数 x
        f(nums, j, path, size, ans);
        // 取当前数，1个、2个...
        for (; i < j; i++) {
            path[size++] = nums[i];
            f(nums, j, path, size, ans);
        }
    }
}
