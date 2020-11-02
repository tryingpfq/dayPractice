package com.tryingpfq.lock;

import sun.awt.windows.ThemeReader;

/**
 * @author tryingpfq
 * @date 2020/11/2
 **/
public class DeadLockDemo {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (object1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.err.println("wait object2");
                }
            }
        },"deadLock-demo-1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (object2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.err.println("wait object1");
                }
            }
        },"deadLock-demo-2");
        t2.start();


    }
}
