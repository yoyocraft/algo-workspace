package core.greedy_02;

import java.util.Arrays;

/**
 * 会议必须独占时间段的最大会议数量
 * 给定若干会议的开始、结束时间
 * 你参加某个会议的期间，不能参加其他会议
 * 返回你能参加的最大会议数量
 */
public class MeetingMonopoly1 {

    public static int maxMeeting1(int[][] meeting) {
        return f(meeting, meeting.length, 0);
    }

    public static int f(int[][] meeting, int n, int i) {
        int ans = 0;
        if (i == n) {
            for (int j = 0, cur = -1; j < n; j++) {
                if (cur <= meeting[j][0]) {
                    ans++;
                    cur = meeting[j][1];
                }
            }
        } else {
            for (int j = i; j < n; j++) {
                swap(meeting, i, j);
                ans = Math.max(ans, f(meeting, n, i + 1));
                swap(meeting, i, j);
            }
        }
        return ans;
    }

    public static void swap(int[][] meeting, int i, int j) {
        int[] tmp = meeting[i];
        meeting[i] = meeting[j];
        meeting[j] = tmp;
    }

    public static int maxMeeting2(int[][] meeting) {
        // 按照会议结束时间升序排列，优先处理结束时间早的，贪心
        Arrays.sort(meeting, (a, b) -> Integer.compare(a[1], b[1]));
        int n = meeting.length;
        int ans = 0;
        for (int i = 0, cur = -1; i < n; i++) {
            if (cur <= meeting[i][0]) {
                ans++;
                cur = meeting[i][1];
            }
        }
        return ans;
    }

    public static int[][] randomMeeting(int n, int m) {
        int[][] ans = new int[n][2];
        for (int i = 0, s, e; i < n; i++) {
            s = (int) (Math.random() * m) + 1;
            e = (int) (Math.random() * m) + 1;
            if (s == e) {
                ans[i][0] = s;
                ans[i][1] = s + 1;
            } else {
                ans[i][0] = Math.min(s, e);
                ans[i][1] = Math.max(s, e);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int M = 12;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[][] meeting = randomMeeting(n, M);
            int ans1 = maxMeeting1(meeting);
            int ans2 = maxMeeting2(meeting);
            if (ans1 != ans2) {
                System.out.println("出错！");
            }
            if (i % 100 == 0) {
                System.err.println("测试轮次： " + i);
            }
        }
        System.out.println("测试结束");
    }
}
