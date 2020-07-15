package com.tryingpfq.algo.dp;

/**
 * @author tryingpfq
 * @date 2020/7/15
 **/
public class Dp1 {

    public static int treesCount(int n) {
        int[] f = new int[n+1];

        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] += f[j - 1] * f[i - j];
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.err.println(treesCount(17));
    }
}
