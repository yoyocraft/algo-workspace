package ds.graph_topo_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlienDictionary {
    // nothing

    /**
     * https://leetcode.cn/problems/Jf1JuT/
     */
    class Solution {
        public String alienOrder(String[] words) {
            int[] indgree = new int[26];
            Arrays.fill(indgree, -1);
            for (String w : words) {
                for (int i = 0; i < w.length(); i++) {
                    indgree[w.charAt(i) - 'a'] = 0;
                }
            }

            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0, j, len; i < words.length - 1; i++) {
                String cur = words[i], nxt = words[i + 1];
                j = 0;
                len = Math.min(cur.length(), nxt.length());
                for (; j < len; j++) {
                    if (cur.charAt(j) != nxt.charAt(j)) {
                        g.get(cur.charAt(j) - 'a').add(nxt.charAt(j) - 'a');
                        indgree[nxt.charAt(j) - 'a']++;
                        break;
                    }
                }
                if (j < cur.length() && j == nxt.length()) {
                    return "";
                }
            }

            int[] que = new int[26];
            int l = 0, r = 0;
            int kinds = 0;
            for (int i = 0; i < 26; i++) {
                if (indgree[i] != -1) {
                    kinds++;
                }
                if (indgree[i] == 0) {
                    que[r++] = i;
                }
            }

            StringBuilder ans = new StringBuilder();
            while (l < r) {
                int cur = que[l++];
                ans.append((char) (cur + 'a'));
                for (int nxt : g.get(cur)) {
                    if (--indgree[nxt] == 0) {
                        que[r++] = nxt;
                    }
                }
            }
            return ans.length() == kinds ? ans.toString() : "";
        }
    }

}