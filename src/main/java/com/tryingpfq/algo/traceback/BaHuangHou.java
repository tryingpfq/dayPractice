package com.tryingpfq.algo.traceback;

import com.google.inject.internal.cglib.proxy.$CallbackFilter;

/**
 * 八皇后问题
 * <p>在8*8的格子中，放入八个棋子，每个棋子所在行列对角线都不存在其他棋子<p/>
 * @author tryingpfq
 * @date 2020/4/9
 **/
public class BaHuangHou {
    /**
     * 索引表示行，值表示列
     */
    private static int result[] = new int[8];

    /**
     * 每一行都需要放入一个棋子
     * 递归放入
     * @param row
     */
    public void cal8Queue(int row) {
        //棋子全部放好了
        if (row == 8) {
            printQueue();
            return;
        }
        for (int column = 0; column < 8; column++) {
            //每一行都有八种放法
            if (isOk(row, column)) {
                result[row] = column;
                cal8Queue(row + 1);
            }
        }
    }

    public boolean isOk(int row, int colunm) {
        int leftUp = colunm - 1,rightUp = colunm + 1;
        for (int i = row - 1; i >= 0; i--) {
            //逐行往上检查
            if (result[i] == colunm) {//判断同列是否有
                return false;
            }
            if (leftUp > 0 && result[i] == leftUp) {//左对角线判断
                return false;
            }
            if (rightUp < 8 && result[i] == rightUp) {//右上对角线
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    /**
     *   Q-*-*-*-*-*-*-*
     *   *-*-Q-*-*-*-*-*
     */
    public void printQueue(){
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column)
                    System.out.print("Q ");
                else System.out.print("* ");
            } System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BaHuangHou baHuangHou = new BaHuangHou();
        baHuangHou.cal8Queue(0);
    }
}
