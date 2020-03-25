package com.tryingpfq.design.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2020/3/25
 **/
public class HandlerChain2 {
    private List<IHandler> handlers = new ArrayList<>();
    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public void handle() {
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) { break;
            }
        }
    }
}
