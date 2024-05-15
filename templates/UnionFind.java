package templates;

class UnionFind {
    public static int MAXN = 1_000_001;

    public static int[] father = new int[MAXN];

    public static int[] size = new int[MAXN];

    public static int[] stack = new int[MAXN];

    public static int n; // tobe initialized

    public static void build() {
        for (int i = 0; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 从 i 号节点开始一直往上找，直到找到代表节点
     */
    public static int find(int i) {
        // 记录沿途收集了多少个节点，做路径扁平化
        int size = 0;
        while (i != father[i]) {
            stack[size++] = i;
            i = father[i];
        }

        // 沿途节点收集完毕，此时 i 为代表节点
        while (size > 0) {
            father[stack[--size]] = i; // 路径扁平化
        }
        return i;
    }

    /**
     * 判断 x 和 y 时候是在同一个集合
     */
    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * 合并集合 x 和集合 y
     */
    public static void union(int x, int y) {
        int fx = find(x); // fx 为 x 所在集合的代表节点
        int fy = find(y); // fy 为 y 所在集合的代表节点

        if (fx == fy)
            return;

        // 小集合挂载到大集合上
        if (size[fx] >= size[fy]) {
            size[fx] += size[fy];
            father[fy] = fx;
        } else {
            size[fy] += size[fx];
            father[fx] = fy;
        }
    }
}
