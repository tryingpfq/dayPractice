package com.tryingpfq.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author Tryingpfq
 * @Time 2019/7/22 23:03
 */
public class TeamProxy implements InvocationHandler {
    private TeamService target;

    public TeamProxy(TeamService t){
        this.target = t ;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(target, args);    // 这里如果传入的是 proxy的话会循环调用自己  TODO 为什么呢
        after();
        return null;
    }

    public void before(){
        System.out.println("do before");
    }

    public void after(){
        System.out.println("do after");
    }
}
