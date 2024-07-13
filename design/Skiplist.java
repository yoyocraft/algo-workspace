package design;

import java.util.Arrays;

/**
 * Skiplist
 */
public class Skiplist {

    static class Node {
        int val;
        Node[] next;

        public Node(int val, int maxLevel) {
            this.val = val;
            this.next = new Node[maxLevel];
        }
    }

    private static final int MAX_LEVEL = 32;
    private static final double SKIPLIST_P = 0.5;
    private Node dummyHead;
    private int curLevel;

    public Skiplist() {
        this.dummyHead = new Node(-1, MAX_LEVEL);
        this.curLevel = 0;
    }

    public boolean search(int target) {
        Node cur = dummyHead;
        for (int i = curLevel - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 target 的元素
            while (cur.next[i] != null && cur.next[i].val < target) {
                cur = cur.next[i];
            }
        }
        // 走到这里 cur.next[0] 一定是第 0 层小于且最接近 target 的元素
        return cur.next[0] != null && cur.next[0].val == target;
    }

    public void add(int num) {
        // 找到所有需要更新的节点
        Node[] updates = new Node[MAX_LEVEL];
        Arrays.fill(updates, dummyHead);
        Node cur = dummyHead;
        cur = findUpdates(num, updates, cur);

        int level = randomLevel();
        // 更新最高的层数
        this.curLevel = Math.max(this.curLevel, level);
        Node newNode = new Node(num, level);
        // 插入新节点
        for (int i = 0; i < level; i++) {
            newNode.next[i] = updates[i].next[i];
            updates[i].next[i] = newNode;
        }
    }

    public boolean erase(int num) {
        Node[] updates = new Node[MAX_LEVEL];
        Node cur = this.dummyHead;
        cur = findUpdates(num, updates, cur);
        // 找到第 0 层小于且最接近 num 的元素
        cur = cur.next[0];
        if (cur == null || cur.val != num) {
            return false;
        }

        // 删除节点
        for (int i = 0; i < curLevel; i++) {
            if (updates[i].next[i] != cur) {
                break;
            }
            // 删除第 i 层的链接
            updates[i].next[i] = cur.next[i];
        }

        // 优化：删除 highest 层为空的节点
        while (curLevel > 1 && dummyHead.next[curLevel - 1] == null) {
            curLevel--;
        }
        return true;
    }

    private Node findUpdates(int num, Node[] updates, Node cur) {
        for (int i = curLevel - 1; i >= 0; i--) {
            // 找到第 i 层小于且最接近 num 的元素
            while (cur.next[i] != null && cur.next[i].val < num) {
                cur = cur.next[i];
            }
            updates[i] = cur;
        }
        return cur;
    }

    /**
     * 随机生成跳表的层级。
     * 
     * 跳表的层级是影响其查询和插入性能的关键因素之一。本方法采用 1/2 的概率增加层级。
     * 
     * @return 生成的随机层级。返回的层级范围在1到MAX_LEVEL之间，其中MAX_LEVEL是跳表允许的最大层级。
     *         层级越高，跳表的查询效率理论上越高，但同时也会增加空间消耗。
     */
    private int randomLevel() {
        // 初始化层级为1，即最低层级
        int level = 1;
        // 当随机数小于SKIPLIST_P且当前层级小于MAX_LEVEL时，增加层级
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level++;
        }
        // 返回计算得到的随机层级
        return level;
    }
}