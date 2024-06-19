package core.linkedlist;

import core.linkedlist.model.ListNode;

/**
 * https://leetcode.cn/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        return solve1(head);
    }

    public static ListNode left;

    public static boolean solve1(ListNode head) {
        left = head;
        return recur(head);
    }

    public static boolean recur(ListNode right) {
        if (right == null) {
            return true;
        }

        if (!recur(right.next)) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }
        // 左指针右移
        left = left.next;
        // 右指针左移
        return true;
    }

    public static boolean solve2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode mid = findMid(head);
        ListNode head2 = mid.next;
        mid.next = null;

        head2 = reverse(head2);

        boolean ans = true;
        ListNode p1 = head, p2 = head2;
        while (ans && p2 != null) {
            if (p1.val != p2.val) {
                ans = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        mid.next = reverse(head2);
        return ans;
    }

    public static ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

}
