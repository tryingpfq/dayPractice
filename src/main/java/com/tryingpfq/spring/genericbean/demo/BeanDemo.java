package com.tryingpfq.spring.genericbean.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2020/3/20
 **/
@Component
public class BeanDemo {

    @Autowired
    private GenericBean<Object,Object> objectObjectGenericBean;

    @Autowired
    private GenericBean<String,String> stringGenericBean;

    @Autowired
    private List<GenericBean> list;

    @Autowired
    private Parent parentOne;


    public void find(){
        System.err.println("start find");
        objectObjectGenericBean.doTest();
        stringGenericBean.doTest();
        list.size();
        System.err.println("end find");
    }

}
