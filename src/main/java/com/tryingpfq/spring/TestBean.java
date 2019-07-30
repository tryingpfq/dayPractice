package com.tryingpfq.spring;

import org.springframework.stereotype.Component;

/**
 * @author tryingpfq
 * @date 2019/7/24 11:23
 */
public class TestBean {

    private String name;
    private int id;


    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
