package com.tryingpfq.conc.future;

/**
 * @Author Tryingpfq
 * @Time 2019/8/4 11:47
 * 完成任务
 */
public interface Task<T,P> {
    T doTask(P param);
}
