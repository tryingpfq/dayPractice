package com.tryingpfq.atomic;

import com.tryingpfq.bigdata.hadoop.mapreduce.WCMapper;
import com.tryingpfq.byteCode.Base;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author tryingpfq
 * @date 2020/5/25
 **/
public class RefrenceDemo {
    private volatile Object val = null;


    private static final AtomicReferenceFieldUpdater<RefrenceDemo, Object>
            updater = AtomicReferenceFieldUpdater.newUpdater(RefrenceDemo.class, Object.class, "val");


    public final Object val(Object obj) {
        return updater.getAndSet(this, obj);
    }

    public Object getVal() {
        return val;
    }

    public static void main(String[] args) {
        RefrenceDemo demo = new RefrenceDemo();
        Base base = new Base();
        demo.val(base);
        Base base2 = (Base) demo.getVal();
        System.out.println(base2);
    }
}
