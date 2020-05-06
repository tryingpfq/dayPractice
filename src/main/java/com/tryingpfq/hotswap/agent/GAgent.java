package com.tryingpfq.hotswap.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author tryingpfq
 * @date 2020/5/6
 **/
public class GAgent {
    private static Instrumentation ist;

    public static void agentmain(String args, Instrumentation ist) {

    }

    public static void premain(String args, Instrumentation ist) {

        /**
         * 其实加载逻辑可以下载这里的，
         * 但可以通过反射获取这个ist就好，在外层进行热替换一样
         */
        GAgent.ist = ist;

    }
}
