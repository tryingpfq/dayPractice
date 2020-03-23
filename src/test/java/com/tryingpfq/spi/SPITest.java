package com.tryingpfq.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author tryingpfq
 * @date 2020/3/23
 **/
public class SPITest {

    @Test
    public void test(){
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.err.println("Java spi ");
        serviceLoader.forEach(Robot::sayHello);
    }
}
