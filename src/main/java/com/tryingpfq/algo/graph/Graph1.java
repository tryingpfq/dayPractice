package com.tryingpfq.algo.graph;

/**
 *
 * 二分图判断 leetcode 785
 *
 * @author tryingpfq
 * @date 2020/7/16
 **/
public class Graph1 {

    private static final int WHITE = 0;//没有被染

    private static final int RED = 1;

    private static final int GREEN = 2;

    private int[] color;

    private boolean result = true;


    public boolean isBipartite(int[][] graph) {

        // 顶点数
        int n = graph.length;
        //默认是0  不需要再初始化了
        color = new int[n];

        for (int i = 0; i < n && result; i++) {
            //如果确定是连通图就不需要循环了，因为连通图从任意一个起点都可以全部遍历到所有顶点的
            if (color[i] == WHITE) {
                dfs(i, RED, graph);
            }
        }
        return result;
    }

    /**
     * 深度优先遍历
     *
     * @param node  起点
     * @param mark  颜色
     * @param graph
     */
    public void dfs(int node, int mark, int[][] graph) {
        //进行标记
        color[node] = mark;

        int _mark = mark == RED ? GREEN : RED;
        //遍历当前节点相邻
        for (int neighbor : graph[node]) {
            if (color[neighbor] == WHITE) {
                //递归调用
                dfs(neighbor, _mark, graph);
                if (!result) {
                    return;
                }
            } else if (color[neighbor] != _mark) {
                result = false;
                return;
            }
        }
    }
}
