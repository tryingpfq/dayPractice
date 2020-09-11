package com.tryingpfq.algo.heep;

/**
 * @author tryingpfq
 * @date 2020/9/11
 **/
public class Heep {
    //从1开始存储数据
    private int[] a;

    //最大数量
    private int n;

    //当前存储的数量
    private int count;

    public Heep(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) {
            return;
        }
        ++count;
        a[count] = data;
        int i = count;
        //自下往上堆化
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = a[count];
        --count;

    }

    //自上往下堆化
    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1])
                maxPos = i * 2 + 1;
            if (maxPos == i)
                break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }


    /**
     * 建堆
     *
     * @param a
     * @param n
     */
    public static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a,k,1);
        }
    }


    private static void swap(int[] a, int i, int j) {

    }
}
