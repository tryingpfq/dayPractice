package com.tryingpfq.spring.aop.demo;

import java.lang.reflect.Proxy;

/**
 * @author tryingpfq
 * @date 2020/4/14
 **/
public class ProxyFactory {
    //维护目标对象
    private static Object target;

    //维护关键点代码的类
    private static Aop aop;
    public static Object getProxyInstance(Object target_, Aop aop_) {

        //目标对象和关键点代码的类都是通过外界传递进来
        target = target_;
        aop = aop_;

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    aop.begin();
                    Object returnValue = method.invoke(target, args);
                    aop.close();
                    return returnValue;
                }
        );
    }
}
