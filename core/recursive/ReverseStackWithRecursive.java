package core.recursive;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 递归函数逆序栈
 */
public class ReverseStackWithRecursive {

    public static void reverse(Deque<Integer> st) {
        if (st.isEmpty()) {
            return;
        }

        int last = bottomOut(st);
        reverse(st);
        st.push(last);
    }

    /**
     * 栈底元素移至栈顶，其余元素顺序保持不变
     * 
     * @return 栈底元素
     */
    public static int bottomOut(Deque<Integer> st) {
        int ans = st.pop();
        if (st.isEmpty()) {
            return ans;
        } else {
            int last = bottomOut(st);
            st.push(ans);
            return last;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        reverse(st);
        print(st);
    }

    public static void print(Deque<Integer> st) {
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();
    }

}
