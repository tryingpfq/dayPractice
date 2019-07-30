package com.tryingpfq.spring;

import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;

/**
 * @author tryingpfq
 * @date 2019/7/24 14:46
 */
public class NoNameBean {

    private int id;

    private String name;

   // Ab

    public NoNameBean(int id, String name) {
        this.id = id;
        this.name =name;
    }
}
