package core.recursive;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用递归排序栈
 * 
 * 栈只提供push、pop、isEmpty三个方法
 * 请完成无序栈的排序，要求排完序之后，从栈顶到栈底从小到大
 * 只能使用栈提供的push、pop、isEmpty三个方法、以及递归函数
 * 除此之外不能使用任何的容器，数组也不行
 * 就是排序过程中只能用：
 * 1) 栈提供的push、pop、isEmpty三个方法
 * 2) 递归函数，并且返回值最多为单个整数
 */
public class SortStackWithRecursive {

    /**
     * 对栈进行排序。
     * 通过迭代最大堆的特性，将栈中的元素重新排序。该方法不直接修改栈，而是通过临时变量和计算来实现排序。
     * 
     * @param st 待排序的栈，该栈应至少包含一个元素。
     */
    public static void sort(Deque<Integer> st) {
        // 计算栈的深度，用于后续的排序迭代。
        int deep = deep(st);
        // 当栈的深度大于0时，继续排序过程。
        while (deep > 0) {
            // 找到当前栈中深度为deep的最大元素。
            int max = max(st, deep);
            // 计算需要交换的次数，以确保最大元素被正确地放置在栈顶。
            int k = times(st, deep, max);
            // 将最大元素向下移动到正确的位置，以维护最大堆的性质。
            down(st, deep, max, k);
            // 更新栈的深度，考虑到已经进行的交换操作。
            deep -= k;
        }
    }

    /**
     * 计算栈的深度
     * 该方法通过递归方式计算给定栈的深度。它从栈中弹出一个元素以减少栈的深度，
     * 然后递归地计算剩余栈的深度，最后将弹出的元素重新压入栈中。
     * 
     * @param st 待计算深度的栈，类型为双端队列，用于模拟栈的操作。
     * @return 返回栈的深度。
     */
    public static int deep(Deque<Integer> st) {
        // 如果栈为空，则深度为0
        if (st.isEmpty()) {
            return 0;
        }

        // 弹出栈顶元素以减少栈的深度
        int num = st.poll();
        // 递归计算剩余栈的深度，并加1以得到当前栈的深度
        int deep = deep(st) + 1;
        // 将之前弹出的元素重新压入栈中，保持栈的原始状态
        st.push(num);

        // 返回计算得到的栈深度
        return deep;
    }

    /**
     * 获取栈中指定深度的最大值。
     * 该方法通过递归方式操作栈，以找出栈中指定深度的元素，并返回该深度元素的最大值。
     * 如果指定的深度为0，表示要获取栈顶元素，但若栈为空，则返回Integer.MIN_VALUE作为默认最大值。
     *
     * @param st   待操作的栈，类型为整数的双端队列。
     * @param deep 指定的深度，用于确定要获取的元素的位置。
     * @return 返回栈中指定深度元素的最大值。
     */
    public static int max(Deque<Integer> st, int deep) {
        // 如果指定深度为0，且栈为空，返回最小整数值
        if (deep == 0) {
            return Integer.MIN_VALUE;
        }

        // 弹出栈顶元素，以进行后续比较
        int num = st.pop();
        // 递归调用，以获取去除栈顶元素后的栈中指定深度的最大值
        int restMax = max(st, deep - 1);
        // 比较当前弹出的元素和栈中剩余元素的最大值，取较大者
        int max = Math.max(restMax, num);
        // 将之前弹出的元素重新压入栈中，保持栈的原始状态
        st.push(num);
        // 返回计算出的最大值
        return max;
    }

    /**
     * 计算栈中最大值出现的次数。
     * 该方法通过递归方式操作栈，每次弹出栈顶元素，直到栈为空。然后计算剩余元素中最大值的出现次数，
     * 并将弹出的元素重新压入栈中，以保持栈的原始状态。
     * 
     * @param st   待操作的栈，类型为整数的双端队列。
     * @param deep 当前操作的深度，用于控制递归的层次。
     * @param max  栈中需要查找的最大值。
     * @return 返回最大值在栈中出现的次数。
     */
    public static int times(Deque<Integer> st, int deep, int max) {
        // 当深度为0时，表示栈已空，返回0次
        if (deep == 0) {
            return 0;
        }
        // 弹出栈顶元素
        int num = st.pop();
        // 递归计算剩余元素中最大值出现的次数
        int restTimes = times(st, deep - 1, max);
        // 计算当前元素是否是最大值，如果是，则次数加1
        int times = restTimes + (num == max ? 1 : 0);
        // 将弹出的元素重新压入栈中，保持栈的原始状态
        st.push(num);
        // 返回最大值出现的总次数
        return times;
    }

    /**
     * 将栈顶元素向下移动，确保最大值位于栈顶。
     * 该方法通过递归操作调整栈的顶部元素，以满足特定条件：将最大值移动到栈顶，并在过程中保持栈的原始顺序。
     * 
     * @param st   待操作的双端队列，模拟栈结构。
     * @param deep 当前操作的深度，用于控制递归的层次。
     * @param max  栈中的最大值，用于比较和调整栈顶元素。
     * @param k    每次向下移动的元素数量，用于控制调整的幅度。
     */
    public static void down(Deque<Integer> st, int deep, int max, int k) {
        // 当深度为0时，将最大值压入栈顶k次，以确保最大值位于栈顶。
        if (deep == 0) {
            for (int i = 0; i < k; i++) {
                st.push(max);
            }
        } else {
            // 弹出栈顶元素，准备进行深度减1的递归操作。
            int num = st.pop();
            // 递归调用down方法，继续调整栈的下一层。
            down(st, deep - 1, max, k);
            // 如果弹出的元素不是最大值，则将其重新压入栈中，保持原始顺序。
            if (num != max) {
                st.push(num);
            }
        }
    }

    public static void main(String[] args) {
        Deque<Integer> st = randomSt(10, 10);
        sort(st);
        printSt(st);

        final int N = 20, V = 20;
        final int testTimes = 20_000;
        System.out.println("test begin");
        for (int i = 1; i <= testTimes; i++) {
            Deque<Integer> st1 = randomSt(N, V);
            sort(st1);
            if (!isSorted(st1)) {
                System.out.println("Oops!");
            }
            if (i % 1000 == 0) {
                System.out.println("test " + i + " times");
            }
        }
        System.out.println("test end");
    }

    public static Deque<Integer> randomSt(int n, int v) {
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st.push((int) (Math.random() * v));
        }
        return st;
    }

    public static void printSt(Deque<Integer> st) {
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Deque<Integer> st) {
        int step = Integer.MIN_VALUE;
        while (!st.isEmpty()) {
            int num = st.pop();
            if (num < step) {
                return false;
            }
            step = num;
        }
        return true;
    }

}
