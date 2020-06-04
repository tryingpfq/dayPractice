package com.tryingpfq.lambda1;

import java.util.function.Consumer;

/**
 * @author tryingpfq
 * @date 2020/5/5
 **/
public class Lambda3 {

    static{
        System.setProperty("jdk.internal.lambda.dumpProxyClasses", ".");
    }

    public static void main(String[] args) {
//        Consumer<String> c = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.err.println(s);
//            }
//        };
//        c.accept("aaa");
        test();
    }

    public static void test(){
        Integer a = 2;
        Integer b = 3;
        Integer c = null;
        boolean flag = false;
        Integer result = flag ? a * b : c;
        System.err.println(result);
    }
}
