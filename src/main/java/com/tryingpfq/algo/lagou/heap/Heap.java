package com.tryingpfq.algo.lagou.heap;

/**
 * 大堆
 */
public class Heap {

    private int data[] = null;

    private int n = 0;

    private int capacity;

    public Heap(int capacity) {
        this.data = new int[capacity];
        this.capacity = capacity;
    }

    public int getSize() {
        return n;
    }

    /**
     * 下沉调整
     * @param i
     */
    public void sink(int i) {
        int t = data[i];
        int j = 0;
        while ((j = (i << 1 + 1)) < n) {
            if (j < n - 1 && data[j] < data[j + 1]) {
                j++;
            }
            if (data[j] > t) {
                data[i] = data[j];
                i = j;
            }else{
                break;
            }
        }
        data[i] = t;
    }

    /**
     * 上浮
     * @param i
     */
    public void swim(int i) {
        int t = data[i];
        int par = 0;
        while ((i > 0 && (par = (n -1) >> 1) != i)) {
            if (data[par] < t) {
                data[i] = data[par];
                i = par;
            }else{
                break;
            }
        }
        data[i] = t;
    }

    public void push(int v) {
        data[n++] = v;
        sink(n -1);
    }

    public int pop() {
        int ret = data[0];
        data[0] = data[--n];
        sink(0);
        return ret;
    }


    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0 || arr == null || arr.length == 0) {
            return new int[0];
        }

        for (int i = 0; i < arr.length; i++) {
            push(arr[i]);

            if (getSize() > k) {
                pop();
            }
        }
        int[] ans = new int[k];
        int i = 0;
        while (getSize() > 0) {
            ans[i++] = pop();
        }
        return ans;
    }
}
