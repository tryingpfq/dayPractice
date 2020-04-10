package com.tryingpfq.design.visit;

/**
 * @author tryingpfq
 * @date 2020/4/10
 **/
public abstract class ResourceFile {
    private String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    abstract public void accpet(Visitor visitor);
}

