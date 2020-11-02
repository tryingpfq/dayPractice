package com.tryingpfq.design.singleton;

/**
 * @author tryingpfq
 * @date 2020/11/2
 **/
public class EnumSingleton {

    private EnumSingleton() {

    }

    private static EnumSingleton getInstance() {
        return Holder.HOLDER.singleton;
    }

    private enum Holder{
        HOLDER;

        private final EnumSingleton singleton;
        Holder(){
            singleton = new EnumSingleton();
        }

    }
}
