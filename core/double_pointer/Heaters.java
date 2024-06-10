package core.double_pointer;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/heaters/
 */
public class Heaters {
    /**
     * 房子去选供暖器
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {

            while (!check(houses, heaters, i, j)) {
                j++;
            }
            ans = Math.max(ans, Math.abs(houses[i] - heaters[j]));
        }
        return ans;
    }

    /**
     * 当前的地点houses[i]由heaters[j]来供暖是最优的吗？
     * 当前的地点houses[i]由heaters[j]来供暖，产生的半径是a
     * 当前的地点houses[i]由heaters[j + 1]来供暖，产生的半径是b
     * 如果a < b, 说明是最优，供暖不应该跳下一个位置
     * 如果a >= b, 说明不是最优，应该跳下一个位置
     */
    public static boolean check(int[] houses, int[] heaters, int i, int j) {
        return j == heaters.length - 1
                ||
                Math.abs(houses[i] - heaters[j]) < Math.abs(houses[i] - heaters[j + 1]);
    }
}
