package com.tryingpfq.algo.lagou;

/**
 * 循环队列
 */
public class MyCircularQueue {

    private int front = 0 ;

    private int rear = 0;

    private int data[] = null;

    private int used = 0;

    private int capacity = 0;

    public MyCircularQueue(int capacity) {
        this.capacity = capacity;

        this.data = new int[capacity];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        data[rear] = value;
        rear = (rear + 1) / capacity;
        used++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        int ret = data[front];
        front = (front + 1) % capacity;
        used--;
        return true;
    }

    public boolean isFull() {
        return used == capacity;
    }

    public boolean isEmpty() {
        return used == 0;
    }

}
