package com.tryingpfq.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author tryingpfq
 * @date 2020/5/5
 **/
public class FileUrlClassLoader extends URLClassLoader {


    public FileUrlClassLoader(URL[] urls) {
        super(urls);
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        String dir = "C:\\D\\sources\\autosource";
        File file = new File(dir);
        URI uri = file.toURI();
        URL[] urls = {uri.toURL()};

        FileUrlClassLoader classLoader = new FileUrlClassLoader(urls);
        Class<?> aClass = classLoader.loadClass("com.tryingpfq.classloader.CustomDemo");
        System.err.println(aClass.getName());

        Thread.currentThread().getContextClassLoader();
    }
}
