package com.tryingpfq.algo.sort;

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
                    a[j + 1] = temp;
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
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

    public void selectSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            int temp = a[i];
            for (int j = i + 1; j < n; j++) {
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


    /**
     * 归并排序
     *
     * @param a 数组
     * @param n 长度
     */
    public void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n);
    }

    /**
     * 递归 排序调用
     *
     * @param a
     * @param p
     * @param r
     */
    public void mergeSortInternally(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = p + (r - p) >> 1;
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q, r);

        merge(a, p, q, r);
    }

    public void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] temp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                temp[k] = a[i++];
            } else {
                temp[k] = a[j++];
            }
            k++;
        }

        int star = i, end = p;
        if (j <= r) {
            star = j;
            end = r;
        }
        while (star <= end) {
            temp[k++] = a[star++];
        }

        for (i = 0; i <= r - p; ++i) {
            a[p + i] = temp[i];
        }
    }


    /**
     * 快排
     */
    public void quickSort(int a[], int n) {
        quickSortInternally(a, 0, n);
    }

    public static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partition(a, p, r);
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }


    /**
     * 获取分区点位置
     *
     * @param a
     * @param p
     * @param r
     */
    public static int partition(int[] a, int p, int r) {
        //以最后一个作为比较
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    int temp = a[i];
                    a[i++] = a[j];
                    a[j] = temp;
                }
            }
        }
        // 把比较值放到中间
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
        return i;
    }


    public static int coin(int[] coins,int mount){
        int val = dp(coins, mount);
        return val;
    }

    public static int dp(int[] conins,int mount){
        if(mount < 0){
            return -1;
        }
        if(mount == 0){
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : conins) {
            int val = dp(conins,mount - coin);
            if (val == -1) {
                continue;
            }
            res = Math.min(res, 1 + val);
        }
        if (res < Integer.MAX_VALUE) {
            return  res;
        }
        return -1;

    }
    public static void main(String[] args) {
        int coin = coin(new int[]{1, 3, 5}, 24);
        System.err.println(coin);
    }
}
