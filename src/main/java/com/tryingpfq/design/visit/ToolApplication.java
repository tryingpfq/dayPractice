package com.tryingpfq.design.visit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2020/4/10
 **/
public class ToolApplication {
    private static Visitor visitor;

    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = resourceFiles(args[0]);
        for (ResourceFile file : resourceFiles) {
            file.accpet(visitor);
        }
    }

    private static List<ResourceFile> resourceFiles(String path) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        resourceFiles.add(new PDFfile("a.pdf"));
        resourceFiles.add(new WordFfile("b.word"));
        return resourceFiles;
    }
}
