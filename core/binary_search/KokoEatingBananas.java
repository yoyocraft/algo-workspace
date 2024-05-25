package core.binary_search;

/**
 * https://leetcode.cn/problems/koko-eating-bananas/
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int mx = 1;
        for (int x : piles) {
            mx = Math.max(mx, x);
        }

        // 二分上下界: (l, r)
        int l = 0, r = mx, m;
        while (l + 1 < r) {
            m = (r - l) / 2 + l;
            if (check(piles, m, h)) {
                r = m; // 符合题意
            } else {
                l = m; // 耗时过长，不符合题意，需要增速
            }
        }
        return r;
    }

    public static boolean check(int[] piles, int speed, int h) {
        int sum = 0;
        for (int x : piles) {
            // x % speed == 0 ? x / speed : x / speed + 1
            sum += (x + speed - 1) / speed;
            if (sum > h) {
                return false;
            }
        }
        return true;
    }
}