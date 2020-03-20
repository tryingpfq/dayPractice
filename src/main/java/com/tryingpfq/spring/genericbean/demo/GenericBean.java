package com.tryingpfq.spring.genericbean.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tryingpfq
 * @date 2020/3/20
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericBean<T, W> {
    private T t;

    private W w;


    public void doTest(){
        System.err.println("t " + t);
        System.err.println("W " + w);
    }

}
