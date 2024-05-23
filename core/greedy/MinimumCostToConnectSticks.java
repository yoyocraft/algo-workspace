package core.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * https://www.luogu.com.cn/problem/P1090
 * 同 https://leetcode.cn/problems/minimum-cost-to-connect-sticks/
 */
public class MinimumCostToConnectSticks {

    public static int MAXN = 10001;

    public static int[] nums = new int[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(minCost());
            out.flush();
        }
        out.flush();
        out.close();
        br.close();
    }

    // region jdk heap
    // public static PriorityQueue<Integer> jdkHeap = new PriorityQueue<>();

    // public static int minCost() {
    //     jdkHeap.clear();
    //     for (int i = 0; i < n; i++) {
    //         jdkHeap.add(nums[i]);
    //     }
    //     int sum = 0, cur = 0;
    //     while (jdkHeap.size() > 1) {
    //         cur = jdkHeap.poll() + jdkHeap.poll();
    //         sum += cur;
    //         jdkHeap.add(cur);
    //     }
    //     return sum;
    // }
    // endregion

    // region small heap
    public static int[] heap = new int[MAXN];
    public static int size;

    public static void add(int x) {
        heap[size] = x;
        int i = size++;
        while (heap[i] < heap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static int pop() {
        int x = heap[0];
        swap(0, --size);
        int i = 0, l = 1, best;
        while (l < size) {
            best = l + 1 < size && heap[l + 1] < heap[l] ? l + 1 : l;
            best = heap[best] < heap[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(i, best);
            i = best;
            l = i * 2 + 1;
        }
        return x;
    }

    public static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public static int minCost() {
        size = 0;
        for(int i = 0; i < n; i++) {
            add(nums[i]);
        }
        int sum = 0, cur = 0;
        while(size > 1) {
            cur = pop() + pop();
            sum += cur;
            add(cur);
        }
        return sum;
    }
    // endregion
}
