package com.tryingpfq.spring.aop.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author tryingpfq
 * @date 2020/4/14
 **/
@Component
@Aspect
public class Aop {
    @Before("execution(* com.*.*(..))")
    public void begin() {
        System.err.println("开启事务");
    }

    @After("execution(* com.*.*(..))")
    public void close() {
        System.err.println("关闭事务");
    }
}
