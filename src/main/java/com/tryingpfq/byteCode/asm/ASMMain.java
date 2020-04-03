package com.tryingpfq.byteCode.asm;

import com.tryingpfq.byteCode.Base;

/**
 * @author tryingpfq
 * @date 2020/4/3
 **/
public class ASMMain {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.generator("com/tryingpfq/byteCode/Base");
        Base base = new Base();
        base.process();
    }
}
