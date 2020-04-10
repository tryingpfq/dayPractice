package com.tryingpfq.algo.traceback;

/**
 * @author tryingpfq
 * @date 2020/4/10
 **/
public class BeiBao {
    private static int maxW = Integer.MIN_VALUE;

    private int[] weight = {2, 2, 4, 6, 3};

    //物品个数
    private int n = 5;

    //背包承载最大重量
    private int w = 9;

    private boolean[][] mem = new boolean[5][10];//设置一个备忘录

    //这样递归的话，就有很多状态重复计算了
    public void f(int i, int cw) {
        if(cw == w || i == 5){
            if(cw > maxW){
                maxW = cw;
            }
            return;
        }
        f(i + 1, cw);

        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]);
        }
    }

    //这样递归的话，就有很多状态重复计算了
    public void f2(int i, int cw) {
        if(cw == w || i == 5){
            if(cw > maxW){
                maxW = cw;
            }
            return;
        }
        if (mem[i][cw]) {
            return;
        }
        mem[i][cw] = true;
        f(i + 1, cw);

        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]);
        }
    }

    public static void main(String[] args) {
        BeiBao  beiBao = new BeiBao();
        beiBao.f2(0, 0);
        System.err.println(maxW);
    }

}
