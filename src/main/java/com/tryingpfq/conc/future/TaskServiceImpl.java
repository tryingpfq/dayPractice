package com.tryingpfq.conc.future;

/**
 * @Author Tryingpfq
 * @Time 2019/8/4 11:53
 */
public class TaskServiceImpl<T,P> implements ITaskService<T,P> {
    @Override
    public Future<?> submit(final Runnable runnable) {
        FutureTask<Void> futureTask = new FutureTask<Void>();
        new Thread(() ->
                runnable.run(),Thread.currentThread().getName()).start();
        return futureTask;
    }

    @Override
    public Future<?> submit(Task<T,P> task, P param) {
        FutureTask futureTask = new FutureTask();
        new Thread(() ->{
            T reasult = task.doTask(param);
            futureTask.finish(reasult);
        },Thread.currentThread().getName()).start();
        return futureTask;
    }
}
