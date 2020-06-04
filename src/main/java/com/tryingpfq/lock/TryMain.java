package com.tryingpfq.lock;

/**
 * @author tryingpfq
 * @date 2020/4/8
 **/
public class TryMain {
    static int count = 0;
    private static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            synchronized (monitor) {
                for (int i = 0; i < 1000; i++) {
                    count++;
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        Thread.sleep(1000L);
        System.err.println(count);
    }
}
