package com.tryingpfq.asm;

/**
 * @author tryingpfq
 * @date 2020/4/3
 **/
public class ASMMain {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.generator("com/tryingpfq/asm/Base");
        Base base = new Base();
        base.process();
    }
}
