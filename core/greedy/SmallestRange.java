package core.greedy;

import java.util.List;
import java.util.TreeSet;

/**
 * https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/
 */
public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {

        int k = nums.size();
        // 0: 值, 1: list 的索引, 2: 值在 list 中的索引
        TreeSet<int[]> set = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < k; i++) {
            set.add(new int[] { nums.get(i).get(0), i, 0 });
        }

        int mnWidth = Integer.MAX_VALUE; // 最小宽度
        int l = 0, r = 0; // 最窄区间的开头结尾

        int[] mx, mn;
        while (set.size() == k) {
            mx = set.last();
            mn = set.pollFirst();
            if (mx[0] - mn[0] < mnWidth) {
                mnWidth = mx[0] - mn[0];
                l = mn[0];
                r = mx[0];
            }
            if (mn[2] + 1 < nums.get(mn[1]).size()) {
                set.add(new int[] { nums.get(mn[1]).get(mn[2] + 1), mn[1], mn[2] + 1 });
            }
        }
        return new int[] { l, r };
    }

}