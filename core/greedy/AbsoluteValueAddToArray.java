package core.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 「加入差值绝对值直到长度固定」
 * 给定一个非负数组arr，计算任何两个数差值的绝对值
 * 如果arr中没有，都要加入到arr里，但是只加一份
 * 然后新的arr继续计算任何两个数差值的绝对值，
 * 如果arr中没有，都要加入到arr里，但是只加一份
 * 一直到arr大小固定，返回arr最终的长度
 */
public class AbsoluteValueAddToArray {

    public static int len1(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            list.add(num);
            set.add(num);
        }
        while (!finish(list, set))
            ;
        return list.size();
    }

    public static boolean finish(ArrayList<Integer> list, HashSet<Integer> set) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int abs = Math.abs(list.get(i) - list.get(j));
                if (!set.contains(abs)) {
                    list.add(abs);
                    set.add(abs);
                }
            }
        }
        return len == list.size();
    }

    public static int len2(int[] nums) {
        int mx = 0, gcd = 0;
        for (int x : nums) {
            mx = Math.max(x, mx);
            if (x != 0) {
                gcd = x; // 任意一个非 0 的值
            }
        }
        if (gcd == 0) { // 数组中都是 0
            return nums.length;
        }

        HashMap<Integer, Integer> cnts = new HashMap<>(); // 统计数字出现频次
        for (int x : nums) {
            if (x != 0) {
                gcd = gcd(gcd, x);
            }
            cnts.put(x, cnts.getOrDefault(x, 0) + 1);
        }
        int ans = mx / gcd;
        int mxCnt = 0;
        for (Map.Entry<Integer, Integer> entry : cnts.entrySet()) {
            int key = entry.getKey(), val = entry.getValue();
            if (key != 0) {
                ans += val - 1; // 统计重复出现的非 0 值
            }
            mxCnt = Math.max(mxCnt, val);
        }
        // 原始数组有 0, 直接统计
        // 原始数组没有 0, 原始数组最大的数字出现频率 > 1, 补一个 0
        ans += cnts.getOrDefault(0, mxCnt > 1 ? 1 : 0); // 统计 0 值
        return ans;
    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 50, V = 100;
        int testTimes = 2000;
        System.out.println("测试开始!");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] nums = randomArray(n, V);
            int ans1 = len1(nums);
            int ans2 = len2(nums);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }

            if (i % 100 == 0) {
                System.err.println("测试轮次： " + i);
            }
        }
        System.out.println("测试结束");
    }
}
