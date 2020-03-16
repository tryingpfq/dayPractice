package com.tryingpfq.eventbus;

import java.util.concurrent.Executor;

/**
 * @author tryingpfq
 * @date 2020/3/16
 **/
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
