package com.tryingpfq.design.visit;

/**
 * @author tryingpfq
 * @date 2020/4/10
 **/
public interface Visitor {
    void visitor(PDFfile pdFfile);
    void visitor(WordFfile wordFfile);
}
