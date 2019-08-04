package com.tryingpfq.conc.future;

/**
 * @Author Tryingpfq
 * @Time 2019/8/4 11:58
 */
public class FutureTask<T> implements Future<T> {
    private T result;

    private boolean isDone = false;

    private final Object LOCK = new Object();

    @Override
    public T get() {
        synchronized (LOCK) {
            while (!isDone) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 获取到结果 并唤醒阻塞线程
     * @param result
     */
    public void finish(T result){
        synchronized (LOCK) {
            if (isDone) {
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
