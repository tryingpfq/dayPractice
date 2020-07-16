package com.tryingpfq.algo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 邻接表 无向图
 *
 * @author tryingpfq
 * @date 2020/7/16
 **/
public class Graph {
    //顶点的个数
    private int v;

    //邻接表
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        //初始化
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }


    /**
     * 广度优先遍历
     *
     * @param s 起点
     * @param t 终点
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        //初始化prev
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        while (!queue.isEmpty()) {
            //出栈
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        //遍历结束
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    // 是否找到
    private boolean found = false;

    /**
     * 深度优先遍历
     *
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found) {
            return;
        }
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }
}
