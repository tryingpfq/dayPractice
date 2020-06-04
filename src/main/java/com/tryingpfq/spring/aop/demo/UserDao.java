package com.tryingpfq.spring.aop.demo;

import org.springframework.stereotype.Repository;

/**
 * @author tryingpfq
 * @date 2020/4/14
 **/
@Repository
public class UserDao implements IUser{

    @Override
    public void save(){
        System.err.println("sava");
    }

}
