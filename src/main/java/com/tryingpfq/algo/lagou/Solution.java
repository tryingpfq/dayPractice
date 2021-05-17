package com.tryingpfq.algo.lagou;


import java.util.ArrayDeque;

public class Solution {
    private ArrayDeque<Integer> Q = new ArrayDeque<>();

    public void push(int val) {
        while (!Q.isEmpty() && Q.getLast() < val) {
            Q.removeLast();
        }
        Q.addLast(val);

    }

    private void pop(int val) {
        if (!Q.isEmpty() && Q.getFirst() == val) {
            Q.removeFirst();
        }
    }

    public int[] maxSlingingWindow(int[] data, int k) {
        if (data.length < k) {
            return null;
        }
        int[] result = new int[data.length - k + 1];

        for (int i = 0; i < data.length; i++) {
            push(data[i]);
            if (i < k - 1) {
                continue;
            }
            result[i - k + 1] = Q.getFirst();
            pop(data[i - k + 1]);
        }
        return result;
    }
}
