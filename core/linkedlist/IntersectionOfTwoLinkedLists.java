package core.linkedlist;

import core.linkedlist.model.ListNode;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int la = 0, lb = 0;
        ListNode tmpA = headA, tmpB = headB;
        while (tmpA != null) {
            tmpA = tmpA.next;
            la++;
        }
        while (tmpB != null) {
            tmpB = tmpB.next;
            lb++;
        }
        tmpA = headA;
        tmpB = headB;
        int diff = la - lb;
        if (diff < 0) {
            tmpA = headB;
            tmpB = headA;
            diff = -diff;
        }

        while (diff-- > 0) {
            tmpA = tmpA.next;
        }

        while (tmpA != tmpB) {
            tmpA = tmpA.next;
            tmpB = tmpB.next;
        }
        return tmpA;
    }
}
