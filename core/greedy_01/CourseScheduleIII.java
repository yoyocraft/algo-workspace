package core.greedy_01;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CourseScheduleIII {

    /**
     * https://leetcode.cn/problems/course-schedule-iii/
     */
    class Solution {
        public int scheduleCourse(int[][] courses) {
            Arrays.sort(courses, (a, b) -> a[1] - b[1]);
            PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
            int time = 0;
            for (int[] c : courses) {
                if (time + c[0] <= c[1]) {
                    heap.add(c[0]);
                    time += c[0];
                } else {
                    if (!heap.isEmpty() && heap.peek() > c[0]) {
                        time += c[0] - heap.poll();
                        heap.add(c[0]);
                    }
                }
            }
            return heap.size();
        }
    }
}
