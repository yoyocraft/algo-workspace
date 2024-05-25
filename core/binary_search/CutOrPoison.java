package core.binary_search;

/**
 * 刀砍毒杀怪兽问题
 * 怪兽的初始血量是一个整数hp，给出每一回合刀砍和毒杀的数值cuts和poisons
 * 第i回合如果用刀砍，怪兽在这回合会直接损失cuts[i]的血，不再有后续效果
 * 第i回合如果用毒杀，怪兽在这回合不会损失血量，但是之后每回合都损失poisons[i]的血量
 * 并且你选择的所有毒杀效果，在之后的回合都会叠加
 * 两个数组cuts、poisons，长度都是n，代表你一共可以进行n回合
 * 每一回合你只能选择刀砍或者毒杀中的一个动作
 * 如果你在n个回合内没有直接杀死怪兽，意味着你已经无法有新的行动了
 * 但是怪兽如果有中毒效果的话，那么怪兽依然会在血量耗尽的那回合死掉
 * 返回至少多少回合，怪兽会死掉
 * <p>
 * 数据范围 :
 * 1 <= n <= 10^5
 * 1 <= hp <= 10^9
 * 1 <= cuts[i]、poisons[i] <= 10^9
 */
public class CutOrPoison {
    public static int solve1(int[] cuts, int[] poisons, int hp) {
        int sum = 0;
        for (int num : poisons) {
            sum += num;
        }
        int[][][] dp = new int[cuts.length][hp + 1][sum + 1];
        return f1(cuts, poisons, 0, hp, 0, dp);
    }

    // 不做要求
    public static int f1(int[] cuts, int[] poisons, int i, int r, int p, int[][][] dp) {
        r -= p;
        if (r <= 0) {
            return i + 1;
        }
        if (i == cuts.length) {
            if (p == 0) {
                return Integer.MAX_VALUE;
            } else {
                return cuts.length + 1 + (r + p - 1) / p;
            }
        }
        if (dp[i][r][p] != 0) {
            return dp[i][r][p];
        }
        int p1 = r <= cuts[i] ? (i + 1) : f1(cuts, poisons, i + 1, r - cuts[i], p, dp);
        int p2 = f1(cuts, poisons, i + 1, r, p + poisons[i], dp);
        int ans = Math.min(p1, p2);
        dp[i][r][p] = ans;
        return ans;
    }

    /**
     * 二分答案
     * 固定回合数，每个回合选择刀砍还是毒杀就确定了
     */
    public static int solve2(int[] cuts, int[] poisons, int hp) {
        int l = 0, r = hp + 2; // (l, r)
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(cuts, poisons, mid, hp)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static boolean check(int[] cuts, int[] poisons, int limit, long hp) {
        int n = Math.min(cuts.length, limit); // 回合数
        for (int i = 0, j = 1; i < n; i++, j++) {
            // 当前回合选择刀砍还是毒杀，最大收益
            hp -= Math.max((long) cuts[i], (long) (limit - j) * (long) poisons[i]);
            if (hp <= 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        int N = 30;
        int V = 20;
        int H = 300;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] cuts = randomArray(n, V);
            int[] posions = randomArray(n, V);
            int hp = (int) (Math.random() * H) + 1;
            int ans1 = solve1(cuts, posions, hp);
            int ans2 = solve2(cuts, posions, hp);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

    // 对数器测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }
}
