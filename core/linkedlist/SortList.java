package core.linkedlist;

import core.linkedlist.model.ListNode;

/**
 * https://leetcode.cn/problems/sort-list/
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        return mergeSort(head);
    }

    public static ListNode mergeSort(ListNode node) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            return node;
        }

        ListNode mid = findMid(node);
        ListNode node2 = mid.next;
        mid.next = null;

        ListNode left = mergeSort(node);
        ListNode right = mergeSort(node2);

        return merge(left, right);
    }

    public static ListNode findMid(ListNode node) {
        ListNode slow = node, fast = node.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(-1), cur = l;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return l.next;
    }
}
