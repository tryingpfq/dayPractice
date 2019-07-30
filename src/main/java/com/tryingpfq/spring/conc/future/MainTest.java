package com.tryingpfq.spring.conc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author tryingpfq
 * @date 2019/7/30 17:39
 */
public class MainTest {

    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();

        CallableDemo demo = new CallableDemo();

        Future<Integer> future = es.submit(demo);

        try {
            while (future.get() == null) {

            }
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            es.shutdownNow();
        }
    }
}
