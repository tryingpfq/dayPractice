package com.tryingpfq.design.visit;

/**
 * @author tryingpfq
 * @date 2020/4/10
 **/
public class Compressor implements Visitor {


    @Override
    public void visitor(PDFfile pdFfile) {
        System.err.println("do Compressor pdf");
    }

    @Override
    public void visitor(WordFfile wordFfile) {
        System.err.println("do Compressor word");
    }
}
