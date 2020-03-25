package com.tryingpfq.design.chain;

/** 责任链控制器
 * @author tryingpfq
 * @date 2020/3/25
 **/
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setSuccessor(null);
        if (head == null || tail == null) {
            head = handler;
            tail = handler;
        }else{
            tail.setSuccessor(handler);
            tail = handler;
        }
    }

    public void handle(){
        if (head != null) {
            head.handle();
        }
    }

    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }
}
