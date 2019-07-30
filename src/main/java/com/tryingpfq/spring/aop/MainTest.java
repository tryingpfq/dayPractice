package com.tryingpfq.spring.aop;

import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tryingpfq
 * @date 2019/7/29 15:36
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AspectTest test = (AspectTest) context.getBean(AspectTest.class);
        System.out.println(test);
        TestBean bean = (TestBean) context.getBean("test");
        System.out.println(bean);
        bean.test();

    }


}
