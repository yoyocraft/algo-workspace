package core.double_pointer;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/boats-to-save-people/
 */
public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length, l = 0, r = n - 1;
        int ans = 0;
        while (l <= r) {
            int sum = people[l] + people[r];
            if (sum <= limit) {
                l++;
                r--;
            } else {
                r--;
            }
            ans++;
        }
        return ans;
    }
}
