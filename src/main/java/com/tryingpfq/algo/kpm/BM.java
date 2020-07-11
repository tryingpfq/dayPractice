package com.tryingpfq.algo.kpm;

/**
 * @Author tryingpfq
 * @Date 2020/3/21
 */
public class BM {
    private static final int SIZE = 256;

    private static char[] a = new char[]{'a', 'b', 'c', 'd', 'a', 'c'};
    private static char[] b = new char[]{'a', 'c'};

    /**
     * 构建BC hash值(字符串的asscii码) 记录模式串中，每个字符最后出现的位置
     * @param b
     * @param m
     * @param bc
     */
    private static void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int asscii = b[i];
            bc[asscii] = i;
        }
    }

    private static int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE];
        generateBC(b,m,bc);
        int i = 0;
        while (i <= n - m) { //后面长度不够，肯定是不需要比对的
            int j;
            for (j = m - 1; j >= 0; j--) {
                if (a[i + j] != b[j]) {
                    break;
                }
                if (j <= 0) {
                    return i;
                }
            }
            i = i + (j - bc[(a[i + j])]);
        }
        return -1;
    }

    private static void generateGS(char[] b,int m,int[] suffix,boolean[] prefix){
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                suffix[k] = j+1; //j+1表示公共后缀子串在b[0, i]中的起始下标
            }
            if (j == -1) prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
        }
    }

    public static void main(String[] args) {
        System.err.println(bm(a, a.length, b, b.length));
    }
}
