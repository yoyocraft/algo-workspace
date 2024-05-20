package ds.two_dimen_diff;

import java.util.Arrays;

public class StrongestForceField {
    // nothing

    /**
     * https://leetcode.cn/problems/xepqZ5/
     */
    class Solution {
        public int fieldOfGreatestBlessing(int[][] fields) {
            // 1. 统计所有左下和右上坐标
            int n = fields.length;
            // 共有 n*2 个坐标
            long[] xs = new long[n << 1], ys = new long[n << 1];
            for (int i = 0, k = 0, p = 0; i < n; i++) {
                long x = fields[i][0], y = fields[i][1], r = fields[i][2];
                // 离散化处理
                xs[k++] = (x << 1) - r;
                xs[k++] = (x << 1) + r;
                ys[p++] = (y << 1) - r;
                ys[p++] = (y << 1) + r;
            }

            // 2. 排序去重
            int sizex = unique(xs);
            int sizey = unique(ys);

            // 3. 二维差分
            int[][] diff = new int[sizex + 2][sizey + 2];
            for (int i = 0, x1, y1, x2, y2; i < n; i++) {
                long x = fields[i][0], y = fields[i][1], r = fields[i][2];
                x1 = rank(xs, (x << 1) - r, sizex);
                y1 = rank(ys, (y << 1) - r, sizey);
                x2 = rank(xs, (x << 1) + r, sizex);
                y2 = rank(ys, (y << 1) + r, sizey);
                add(diff, x1, y1, x2, y2);
            }

            // 4. 直接在 diff 上复原，计算最大值
            int ans = 0;
            for (int i = 1; i < diff.length; i++) {
                for (int j = 1; j < diff[0].length; j++) {
                    diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                    ans = Math.max(ans, diff[i][j]);
                }
            }
            return ans;
        }

        public static void add(int[][] diff, int x1, int y1, int x2, int y2) {
            diff[x1][y1] += 1;
            diff[x1][y2 + 1] -= 1;
            diff[x2 + 1][y1] -= 1;
            diff[x2 + 1][y2 + 1] += 1;
        }

        public static int unique(long[] a) {
            Arrays.sort(a);
            int size = 1;
            for (int i = 1; i < a.length; i++) {
                if (a[i] != a[size - 1]) {
                    a[size++] = a[i];
                }
            }
            return size;
        }

        // v 一定在 nums[0~size-1]，返回 v 所对应的编号（下标+1） => upperBound
        public static int rank(long[] a, long v, int size) {
            int l = -1, r = size;
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;
                if (a[mid] <= v) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            return r;
        }
    }
}