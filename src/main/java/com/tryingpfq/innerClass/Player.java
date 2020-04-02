package com.tryingpfq.innerClass;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2020/4/2
 **/
public class Player {

    private static List<IExecutor> executors = Lists.newArrayList();


    private InnerClassDemo.MyInnerClass myInnerClass;

    public void addExecutor(IExecutor executor) {
        executors.add(executor);
    }

    public void doExecuror(){
        for (IExecutor executor : executors) {
            executor.executor(this);
        }
    }

    public void setMyInnerClass(InnerClassDemo.MyInnerClass myInnerClass) {
        this.myInnerClass = myInnerClass;
    }

    public void playeDoInner(){
        myInnerClass.doInner();
    }
}
