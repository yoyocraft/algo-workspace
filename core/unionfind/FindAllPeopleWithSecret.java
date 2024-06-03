package core.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-people-with-secret/
 */
public class FindAllPeopleWithSecret {

    public static final int MAXN = 100_001;
    public static final int[] father = new int[MAXN];
    public static final boolean[] secret = new boolean[MAXN];

    public static void build(int n, int first) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            secret[i] = false;
        }

        father[first] = 0;
        secret[0] = true;
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            secret[fy] |= secret[fx];
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        build(n, firstPerson);
        // 按照会议时间排序
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        int m = meetings.length;
        for (int l = 0, r; l < m;) {
            r = l;
            while (r + 1 < m && meetings[l][2] == meetings[r + 1][2]) {
                r++;
            }
            // [l, r] 是一个时间点
            for (int i = l; i <= r; i++) {
                union(meetings[i][0], meetings[i][1]);
            }
            // 拆散不知道秘密的集合
            for (int i = l, a, b; i <= r; i++) {
                a = meetings[i][0];
                b = meetings[i][1];
                if (!secret[find(a)]) {
                    father[a] = a;
                }
                if (!secret[find(b)]) {
                    father[b] = b;
                }
            }
            l = r + 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (secret[find(i)]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
