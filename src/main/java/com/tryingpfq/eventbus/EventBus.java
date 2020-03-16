package com.tryingpfq.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author tryingpfq
 * @date 2020/3/16
 **/
public class EventBus {
    private Executor executor;

    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus(){
        this(MoreExecutors.directExecutor());
    }

    public EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object) {
        registry.register(object);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        if (observerActions == null) {
            return;
        }
        for (ObserverAction action : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    action.exetute(event);
                }
            });
        }
    }
}
