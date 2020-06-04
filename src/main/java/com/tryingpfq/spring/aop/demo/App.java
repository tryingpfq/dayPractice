package com.tryingpfq.spring.aop.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tryingpfq
 * @date 2020/4/14
 **/
public class App {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        IUser iUser = (IUser) context.getBean("userDao");

        iUser.save();
    }
}
