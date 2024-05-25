package core.binary_search;

import java.util.PriorityQueue;

/**
 * 计算等位时间
 * 给定一个数组arr长度为n，表示n个服务员，每服务一个人的时间
 * 给定一个正数m，表示有m个人等位，如果你是刚来的人，请问你需要等多久？
 * 假设m远远大于n，比如n <= 10^3, m <= 10^9，该怎么做是最优解？
 */
public class WaitingTime {

    public static int solve1(int[] arr, int m) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heap.add(new int[] { 0, arr[i] });
        }

        for (int i = 0; i < m; i++) {
            int[] cur = heap.poll();
            cur[0] += cur[1];
            heap.add(cur);
        }
        return heap.peek()[0];
    }

    public static int solve2(int[] arr, int m) {
        int mn = Integer.MAX_VALUE;
        for (int x : arr) {
            mn = Math.min(mn, x);
        }
        int l = -1, r = mn * m; // 服务员工作时间上下界
        while (l + 1 < r) {
            int mid = l + (r - l) / 2; // 取中间时间
            if (check(arr, mid, m)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static boolean check(int[] arr, int time, int m) {
        int sum = 0;
        for (int x : arr) {
            sum += (time / x) + 1;
        }
        return sum > m; // 当前 time 时间可以服务的任务 > m => 可以服务到第 m 个人
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        int N = 50;
        int V = 30;
        int M = 3000;
        int testTime = 20000;
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int m = (int) (Math.random() * M);
            int ans1 = solve1(arr, m);
            int ans2 = solve2(arr, m);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }
}
