package com.tryingpfq.algo.lagou.heap;

import org.apache.xmlbeans.impl.values.XmlIntRestriction;

import java.util.*;

public class Demo {


    private int[] a;

    private int n;

    private int capacity;

    public Demo(int size) {
        this.a = new int[size];
        this.capacity = size;
    }


    /**
     * 先放到最后一个位置，然后在进行上浮动，就是一个大堆了
     *
     * @param v
     */
    public void push(int v) {
        a[n++] = v;
        swim(n - 1);
    }

    public int pop() {
        int ret = a[0];
        a[0] = a[--n];
        sink(0);
        return ret;
    }


    /**
     * 下沉
     *
     * @param i
     */
    private void sink(int i) {
        int j = 0;
        int t = a[i];
        while ((j = (i << 1) + 1) < n) {
            if (j < n - 1 && a[j] < a[j + 1]) {
                j++;
            }
            if (a[j] > t) {
                a[i] = a[j];
                i = j;
            } else {
                break;
            }
        }
        a[i] = t;
    }

    /**
     * 上浮
     *
     * @param i
     */
    public void swim(int i) {
        int t = a[i];
        int par = 0;
        while (i > 0) {
            par = (i - 1) >> 1;
            if (a[par] < t) {
                a[i] = a[par];
                i = par;
            } else {
                break;
            }
        }
        a[i] = t;
    }

    public class Counter extends HashMap<Integer, Integer> {

        public int get(Integer k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(Integer k, Integer v) {
            put(k, get(k));
            if (get(k) <= 0) {
                remove(k);
            }
        }
    }

    class Node {
        public int val = 0;
        public int cnt = 0;

        public Node(int a, int b) {
            val = a;
            cnt = b;
        }
    }

    public int[] topKFrequent(int[] A, int k) {
        final int N = A == null ? 0 : A.length;
        if (k <= 0) {
            return new int[0];
        }
        int[] ans = new int[k];

        Counter h = new Counter();
        for (int i = 0; i < N; i++) {
            h.add(A[i], 1);
        }

        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.cnt));
        for (Map.Entry<Integer, Integer> entry : h.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int i = 0 ;
        while (!queue.isEmpty()) {
            ans[i++] = queue.poll().val;
        }

        Arrays.sort(ans);
        return ans;
    }

}
