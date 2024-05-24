package core.kmp;

public class LinkedListInBinaryTree {

    static class ListNode {
        int val;
        ListNode next;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /**
     * https://leetcode.cn/problems/linked-list-in-binary-tree/
     */
    class Solution {
        public boolean isSubPath(ListNode head, TreeNode root) {
            int m = 0;
            ListNode tmp = head;
            while (tmp != null) {
                m++;
                tmp = tmp.next;
            }
            int[] s2 = new int[m];
            m = 0;
            while (head != null) {
                s2[m++] = head.val;
                head = head.next;
            }

            int[] next = nextArray(s2, m);
            return find(s2, next, root, 0);
        }

        public static int[] nextArray(int[] s, int m) {
            if (m == 1) {
                return new int[] { -1 };
            }

            int[] next = new int[m];
            next[0] = -1;
            next[1] = 0;
            int i = 2, cn = 0;
            while (i < m) {
                if (s[i - 1] == s[cn]) {
                    next[i++] = ++cn;
                } else if (cn > 0) {
                    cn = next[cn];
                } else {
                    next[i++] = 0;
                }
            }
            return next;
        }

        public static boolean find(int[] s, int[] next, TreeNode cur, int i) {
            if (i == s.length) {
                return true;
            }
            if (cur == null) {
                return false;
            }

            while (i >= 0 && cur.val != s[i]) {
                i = next[i];
            }
            return find(s, next, cur.left, i + 1) || find(s, next, cur.right, i + 1);
        }
    }

}
