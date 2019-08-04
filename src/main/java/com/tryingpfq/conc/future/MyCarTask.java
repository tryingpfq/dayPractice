package com.tryingpfq.conc.future;

/**
 * @Author Tryingpfq
 * @Time 2019/8/4 12:03
 */
public class MyCarTask<T,P> implements Task<T,P> {

    @SuppressWarnings("unchecked")
    @Override
    public T doTask(P param) {
        String car = param + "is created success";
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (T) car;
    }
}
