package core.greedy_02;

import java.util.PriorityQueue;

public class IPO {
    class Solution {
        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            int n = profits.length;
            // 0: capital
            // 1: profit
            PriorityQueue<int[]> lock = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 启动资金，小根堆
            PriorityQueue<int[]> unlock = new PriorityQueue<>((a, b) -> b[1] - a[1]); // 纯利润，大跟堆
            for (int i = 0; i < n; i++) {
                lock.add(new int[] { capital[i], profits[i] });
            }

            while (k > 0) {
                while (!lock.isEmpty() && lock.peek()[0] <= w) {
                    unlock.add(lock.poll());
                }
                if (unlock.isEmpty()) {
                    break;
                }
                w += unlock.poll()[1];
                k--;
            }
            return w;
        }
    }
}
