package com.tryingpfq.design.visit;

public class PDFfile extends ResourceFile{

    public PDFfile(String filePath) {
        super(filePath);
    }

    @Override
    public void accpet(Visitor visitor) {
        visitor.visitor(this);
    }
}
