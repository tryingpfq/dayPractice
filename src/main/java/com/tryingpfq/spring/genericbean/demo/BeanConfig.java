package com.tryingpfq.spring.genericbean.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tryingpfq
 * @date 2020/3/20
 **/
@Configuration
public class BeanConfig {

    @Bean
    public Parent parentOne(){
        return new Parent();
    }

    @Bean
    public Parent parentTwo(){
        return new Parent();
    }

    @Bean
    public GenericBean<String,String> stringGeneric(){
        return new GenericBean<String, String>("str1", "str2");
    }

    @Bean
    public GenericBean<Object,Object> objectGeneric(){
        return new GenericBean<Object,Object>("str2", 2);
    }

}
