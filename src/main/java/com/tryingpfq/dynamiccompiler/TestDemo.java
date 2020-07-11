package com.tryingpfq.dynamiccompiler;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class TestDemo {
    public static void main(String[] args) throws InterruptedException {

        HelloWorld helloWorld = new HelloWorld();
        helloWorld.print();

        Thread.sleep(2000);

        System.err.println("start");
        new Thread(() -> {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int status = compiler.run(null, null, null, "-d", System.getProperty("user.dir")+"\\target\\classes","E:\\HelloWorld.java");
            if (status == 1) {
                System.err.println("ok");
            }else{
                System.err.println("fail");
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("after");
        HelloWorld helloWorld1 = new HelloWorld();
        helloWorld.print();
    }
}
