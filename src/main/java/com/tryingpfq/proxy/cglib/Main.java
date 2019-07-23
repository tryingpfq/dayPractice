package com.tryingpfq.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author tryingpfq
 * @date 2019/7/23 14:41
 */
public class Main {
    public static void main(String[] args) {
       // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"out\\proxy");

        //通过CGLib动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        //设置enhancer的父类
        enhancer.setSuperclass(TeamService.class);
        //设置回调对象
        enhancer.setCallback(new MyMethodInterceptor());
        //创建代理对象
        TeamService proxy = (TeamService) enhancer.create();
        proxy.accept(123456L);
    }
}
