package com.tryingpfq.byteCode;

/**
 * @author tryingpfq
 * @date 2020/4/2
 **/
public class ByteCodeDemo {
    private int a;

    public int add(){
        int b = 2;
        int c = a + b;
        System.out.println(c);
        return c;
    }

    public static void dev(){
        int a = 1;
    }
}
