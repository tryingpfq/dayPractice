package com.tryingpfq.spring.genericbean;

import com.tryingpfq.spring.genericbean.demo.BeanConfig;
import com.tryingpfq.spring.genericbean.demo.BeanDemo;
import com.tryingpfq.spring.genericbean.demo.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tryingpfq
 * @date 2020/3/20
 **/
public class Main {

    private static ApplicationContext context;
    static{
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
        BeanDemo beanConfig = context.getBean(BeanDemo.class);
        beanConfig.find();
        System.err.println(beanConfig);
    }
}
