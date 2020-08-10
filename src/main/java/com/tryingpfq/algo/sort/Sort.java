package com.tryingpfq.algo.sort;

import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;

/**
 * @author tryingpfq
 * @date 2020/8/10
 **/
public class Sort {

    public void bubbleSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public void insertionSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = value;
        }
    }

    public void selectSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n-1; i++) {
            int k = i;
            int temp = a[i];
            for (int j = i+1; j < n; j++) {
                if (a[j] < temp) {
                    temp = a[j];
                    k = j;
                }
            }
            if (k != i) {
                a[i] = a[k];
                a[k] = temp;
            }
        }
    }
}
