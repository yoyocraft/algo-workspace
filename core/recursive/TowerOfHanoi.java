package core.recursive;

/**
 * 汉诺塔问题
 */
public class TowerOfHanoi {

    public static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("Move 1 from " + a + " to " + c);
        } else {
            hanoi(n - 1, a, c, b);
            System.out.println("Move " + n + " from " + a + " to " + c);
            hanoi(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }
}
