package com.tryingpfq.proAndCon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2019/7/2 14:55
 */
public class MyThread {
    private static Integer count = 0;

    private static final Integer FULL = 10;

    private static String LOCK = "lock"; // 资源锁


    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        long start = System.currentTimeMillis();

        List<Thread> list = new ArrayList<Thread>();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(myThread.new Producer());
            thread.start();
            list.add(thread);
        }

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(myThread.new Consumer());
            thread.start();
            list.add(thread);
        }

        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("子线程执行时长：" + (end - start));
    }


    //producer
    class Producer implements Runnable{

        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == FULL) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count++;
                        System.out.println(Thread.currentThread().getName());
                        LOCK.notifyAll();
                    }
                }
            }
        }
    }

    //consumer
    class Consumer implements Runnable{

        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName());
                    LOCK.notifyAll();
                }
            }
        }
    }
}

