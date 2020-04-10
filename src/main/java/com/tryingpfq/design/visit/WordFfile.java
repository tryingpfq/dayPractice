package com.tryingpfq.design.visit;

public class WordFfile extends ResourceFile{

    public WordFfile(String filePath) {
        super(filePath);
    }

    @Override
    public void accpet(Visitor visitor) {
        visitor.visitor(this);
    }

}
