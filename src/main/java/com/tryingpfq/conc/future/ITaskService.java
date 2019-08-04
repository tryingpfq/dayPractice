package com.tryingpfq.conc.future;


/**
 * @Author Tryingpfq
 * @Time 2019/8/4 11:49
 *  提交任务
 */
public interface ITaskService<T,P> {
    Future<?> submit(Runnable runnable);

    Future<?> submit(Task<T, P> task, P param);

}

