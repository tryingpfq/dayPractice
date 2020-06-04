package com.tryingpfq.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tryingpfq
 * @date 2020/4/15
 **/
public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        // 记录debug级别的信息
        log.debug("This is debug message.");
        // 记录info级别的信息
        log.info("This is info message.");
        // 记录error级别的信息
        log.error("This is error message.");
    }
}
