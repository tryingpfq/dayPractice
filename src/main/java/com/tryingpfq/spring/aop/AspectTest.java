package com.tryingpfq.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;

/**
 * @author tryingpfq
 * @date 2019/7/29 14:51
 */
@Aspect
public class AspectTest {

    @Pointcut("execution(* *.test(..))")
    public void test(){
        System.out.println();
    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("before");
    }

    @After("test()")
    public void afterTest(){
        System.out.println("after");
    }
}
