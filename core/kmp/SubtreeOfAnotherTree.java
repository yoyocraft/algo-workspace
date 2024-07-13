package core.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subtree-of-another-tree/
 */
public class SubtreeOfAnotherTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // return isSubtreeByRecur(root, subRoot);
        return isSubtreeByKmp(root, subRoot);
    }

    // region recur
    public static boolean isSubtreeByRecur(TreeNode root, TreeNode subRoot) {
        if (root != null && subRoot != null) {
            return same(root, subRoot) || isSubtreeByRecur(root.left, subRoot)
                    || isSubtreeByRecur(root.right, subRoot);
        }
        return subRoot == null;
    }

    public static boolean same(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val && same(t1.left, t2.left) && same(t1.right, t2.right);
    }

    // endregion

    // region kmp
    public static boolean isSubtreeByKmp(TreeNode root, TreeNode subRoot) {
        if (root != null && subRoot != null) {
            List<String> s1 = new ArrayList<>();
            List<String> s2 = new ArrayList<>();
            serial(root, s1);
            serial(subRoot, s2);
            return kmp(s1, s2) != -1;
        }
        return subRoot == null;
    }

    public static void serial(TreeNode node, List<String> path) {
        if (node == null) {
            path.add(null);
        } else {
            path.add(String.valueOf(node.val));
            serial(node.left, path);
            serial(node.right, path);
        }
    }

    public static int kmp(List<String> s, List<String> t) {
        int n = s.size(), m = t.size(), x = 0, y = 0;
        int[] next = nextArray(t, m);
        while (x < n && y < m) {
            if (isEquals(s.get(x), t.get(y))) {
                x++;
                y++;
            } else if (y > 0) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == m ? x - y : -1;
    }

    public static int[] nextArray(List<String> p, int n) {
        if (n == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i < n) {
            if (isEquals(p.get(i - 1), p.get(cn))) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean isEquals(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.equals(t);
    }

    // endregion

}
