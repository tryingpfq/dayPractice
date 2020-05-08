package com.tryingpfq.design.singleton;

/**
 * @author tryingpfq
 * @date 2020/5/5
 **/
public class StaticClassSingleton {
    private StaticClassSingleton() {

    }

    public static StaticClassSingleton getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        public static StaticClassSingleton INSTANCE = new StaticClassSingleton();
    }
}
