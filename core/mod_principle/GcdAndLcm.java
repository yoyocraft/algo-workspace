package core.mod_principle;

public class GcdAndLcm {

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return (long) a / gcd(a, b) * b;
    }

}
