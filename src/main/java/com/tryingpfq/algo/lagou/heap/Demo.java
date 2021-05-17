package com.tryingpfq.algo.lagou.heap;

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
}
