package core.dp_one_dimen;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/decode-ways-ii/
 */
public class DecodeWaysII {

    public static final int MOD = (int) 1e9 + 7;

    public int numDecodings(String s) {
        return numDecodings1(s);
    }

    public static int numDecodings1(String s) {
        return (int) f1(s.toCharArray(), 0);
    }

    public static long f1(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }

        if (s[i] == '0') {
            return 0;
        }

        long ans = f1(s, i + 1) * (s[i] == '*' ? 9 : 1);
        if (i + 1 < s.length) {
            if (s[i] != '*') {
                if (s[i + 1] != '*') {
                    if (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7')) {
                        ans += f1(s, i + 2);
                    }
                } else {
                    // x*
                    if (s[i] == '1') {
                        ans += f1(s, i + 2) * 9;
                    }
                    if (s[i] == '2') {
                        ans += f1(s, i + 2) * 6;
                    }
                }
            } else {
                if (s[i + 1] != '*') {
                    // *x
                    if (s[i + 1] < '7') {
                        ans += f1(s, i + 2) * 2;
                    } else {
                        ans += f1(s, i + 2);
                    }
                } else {
                    // **
                    ans += f1(s, i + 2) * 15;
                }
            }
        }
        return ans % MOD;
    }

    public static int numDecodings2(String str) {
        char[] s = str.toCharArray();
        long[] memo = new long[s.length + 1];
        Arrays.fill(memo, -1L);
        return (int) f2(s, 0, memo);
    }

    public static long f2(char[] s, int i, long[] memo) {
        if (i == s.length) {
            return 1;
        }

        if (s[i] == '0') {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        long ans = f2(s, i + 1, memo) * (s[i] == '*' ? 9 : 1);
        if (i + 1 < s.length) {
            if (s[i] != '*') {
                if (s[i + 1] != '*') {
                    if (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7')) {
                        ans += f2(s, i + 2, memo);
                    }
                } else {
                    // x*
                    if (s[i] == '1') {
                        ans += f2(s, i + 2, memo) * 9;
                    }
                    if (s[i] == '2') {
                        ans += f2(s, i + 2, memo) * 6;
                    }
                }
            } else {
                if (s[i + 1] != '*') {
                    // *x
                    if (s[i + 1] < '7') {
                        ans += f2(s, i + 2, memo) * 2;
                    } else {
                        ans += f2(s, i + 2, memo);
                    }
                } else {
                    // **
                    ans += f2(s, i + 2, memo) * 15;
                }
            }
        }
        return memo[i] = (ans % MOD);
    }

    public static int numDecodings3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                dp[i] = dp[i + 1] * (s[i] == '*' ? 9 : 1);
                if (i + 1 < s.length) {
                    if (s[i] != '*') {
                        if (s[i + 1] != '*') {
                            if (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7')) {
                                dp[i] += dp[i + 2];
                            }
                        } else {
                            // x*
                            if (s[i] == '1') {
                                dp[i] += dp[i + 2] * 9;
                            }
                            if (s[i] == '2') {
                                dp[i] += dp[i + 2] * 6;
                            }
                        }
                    } else {
                        if (s[i + 1] != '*') {
                            // *x
                            if (s[i + 1] < '7') {
                                dp[i] += dp[i + 2] * 2;
                            } else {
                                dp[i] += dp[i + 2];
                            }
                        } else {
                            // **
                            dp[i] += dp[i + 2] * 15;
                        }
                    }
                }
            }
            dp[i] %= MOD;
        }
        return (int) dp[0];
    }

    public static int numDecodings4(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long dp0 = 0, dp1 = 1, dp2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                dp0 = dp1 * (s[i] == '*' ? 9 : 1);
                if (i + 1 < n) {
                    if (s[i] != '*') {
                        if (s[i + 1] != '*') {
                            if (s[i] < '2' || (s[i] == '2' && s[i + 1] < '7')) {
                                dp0 += dp2;
                            }
                        } else {
                            // x*
                            if (s[i] == '1') {
                                dp0 += dp2 * 9;
                            }
                            if (s[i] == '2') {
                                dp0 += dp2 * 6;
                            }
                        }
                    } else {
                        if (s[i + 1] != '*') {
                            // *x
                            if (s[i + 1] < '7') {
                                dp0 += dp2 * 2;
                            } else {
                                dp0 += dp2;
                            }
                        } else {
                            // **
                            dp0 += dp2 * 15;
                        }
                    }
                }
                dp0 %= MOD;
            }
            dp2 = dp1;
            dp1 = dp0;
            dp0 = 0;
        }
        return (int) dp1;
    }

}
