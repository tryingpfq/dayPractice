package com.tryingpfq.design.visit;

/**
 * @author tryingpfq
 * @date 2020/4/10
 **/
public class Extractor implements Visitor {

    @Override
    public void visitor(PDFfile pdFfile) {
        System.err.println("do extrator pdf");
    }

    @Override
    public void visitor(WordFfile wordFfile) {
        System.err.println("do extrator word");
    }
}
