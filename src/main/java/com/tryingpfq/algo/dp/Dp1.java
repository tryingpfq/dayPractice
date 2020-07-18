package com.tryingpfq.algo.dp;

/**
 * @author tryingpfq
 * @date 2020/7/15
 **/
public class Dp1 {

    public static int treesCount(int n) {
        int[] f = new int[n + 1];

        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] += f[j - 1] * f[i - j];
            }
        }
        return f[n];
    }


    /**
     * 交错字符串 leetcode[97]
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();

        if (l1 + l2 != l3) {
            return false;
        }

        boolean[][] f = new boolean[l1 + 1][l2 + 1];
        f[0][0] = true;
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] |= (f[i-1][j] && s1.charAt(i-1) ==  s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] |= (f[i][j-1] && s2.charAt(j-1) ==  s3.charAt(p));
                }
            }
        }

        return f[l1][l2];
    }


    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac"));
    }
}
