package core.linkedlist;

import core.linkedlist.model.Node;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 1. 复制链表各节点，拼接出新链表
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }

        // 2. 处理新链表每个节点的 random 指向
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 3. 拆分两链表
        cur = head;
        Node ans = cur.next;
        while (cur != null) {
            Node next = cur.next.next;
            Node newNode = cur.next;
            cur.next = next;
            newNode.next = next != null ? next.next : null;
            cur = next;
        }
        return ans;
    }

}
