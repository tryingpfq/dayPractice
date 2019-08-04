package com.tryingpfq.conc.future;

/**
 * @Author Tryingpfq
 * @Time 2019/8/4 11:53
 */
public interface Future<T> {

    T get();

    boolean done();
}
