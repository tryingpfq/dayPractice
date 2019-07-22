package com.tryingpfq.classloader;

/**
 * @author tryingpfq
 * @date 2019/7/22 16:53
 */
public class LoaderDemo {

    public static void main(String[] args) {
        String cLoaser = "com.tryingpfq.classloader.LoaderDemo$LoaderClass";

        String fLoader = "com.tryingpfq.classloader.LoaderDemo$ForNameClass";
        classloaderTest(cLoaser,fLoader);
        forNameLoaderTest(cLoaser,fLoader);
    }

    public static class LoaderClass{
        static {
            System.out.println("loader ");
        }
    }

    public static void classloaderTest(String a, String b) {
        Class<?> aa;
        Class<?> bb;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        try {
            System.out.println("ClassLoader");
            aa = classLoader.loadClass(a);

            bb = classLoader.loadClass(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void forNameLoaderTest(String a, String b) {
        try {
            System.out.println("forName loader");
            Class<?> aa = Class.forName(a);
            Class<?> bb = Class.forName(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static class ForNameClass{
        static {
            System.out.println("loader");
        }
    }
}
