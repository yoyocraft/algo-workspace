package core.greedy_02;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingOneDay {
    /**
     * https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
     */
    class Solution {
        public int maxEvents(int[][] events) {
            int n = events.length;
            // 按照会议开始时间排序
            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            int mn = events[0][0], mx = events[0][1];
            for (int i = 1; i < n; i++) {
                mx = Math.max(mx, events[i][1]);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>();
            int i = 0, ans = 0;
            for (int day = mn; day <= mx; day++) {
                // 当前 day 可以参加的会议
                while (i < n && events[i][0] == day) {
                    heap.add(events[i++][1]);
                }
                // 已经过时的会议
                while (!heap.isEmpty() && heap.peek() < day) {
                    heap.poll();
                }
                // 第 day 天参加会议
                if (!heap.isEmpty()) {
                    heap.poll();
                    ans++;
                }
            }
            return ans;
        }
    }
}
