package com.tryingpfq.spring.conc.future;

import java.util.concurrent.Callable;

/**
 * @author tryingpfq
 * @date 2019/7/30 17:37
 */
public class CallableDemo implements Callable<Integer> {

    private int sum;


    @Override
    public Integer call() throws Exception {
        System.out.println("start");
        Thread.sleep(2000);
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
