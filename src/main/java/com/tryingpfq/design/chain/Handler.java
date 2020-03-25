package com.tryingpfq.design.chain;

/**
 * @author tryingpfq
 * @date 2020/3/25
 **/
public abstract class Handler {
    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }


    public void handle(){
        boolean handled = doHandle();
        if (!handled && successor != null) {
            doHandle();
        }
    }
    public abstract boolean doHandle();
}

class HandlerA extends Handler{

    @Override
    public boolean doHandle() {
       return false;
    }
}

class HandlerB extends Handler{

    @Override
    public boolean doHandle() {

        return false;
    }
}

