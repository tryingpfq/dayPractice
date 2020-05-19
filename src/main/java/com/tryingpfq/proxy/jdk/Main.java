package com.tryingpfq.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author Tryingpfq
 * @Time 2019/7/22 23:08
 */
public class Main {

    public static void main(String[] args) {
        TeamService ts = new TeamServiceImpl();

        // 获取classLoader
        ClassLoader classLoader = ts.getClass().getClassLoader();

        //这一句是生成代理类的class文件，前提是你需要在工程根目录下创建com/sun/proxy目录，不然会报找不到路径的io异常
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        // 所有实现的接口
        Class[] intefaces = ts.getClass().getInterfaces();

        // 设置来着代理传过来的方法调用请求处理器
        InvocationHandler invocationHandler = new TeamProxy(ts);

        // 创建代理对象，JDK会根据传入的参数动态的在内存中穿件.class 文件等同的字节码
        // 然后根据相应的字节码转换成对应的class
        // 然后调用newInstance()创建实例

        Object o = Proxy.newProxyInstance(classLoader, intefaces, invocationHandler);
        System.out.println(o.getClass());
        ((TeamService)o).apply(123456L);
        createProxyFile(ts.getClass(),"$TeamProxyNew");
    }

    public static void createProxyFile(Class clazz,String name){
        byte[] classByete = ProxyGenerator.generateProxyClass(name, clazz.getInterfaces());
        try {

            Files.write(Paths.get("out/proxy/jdk" + File.separator + clazz.getSimpleName().replace('.', File.separatorChar) + ".class"), classByete);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String path = "out\\proxy\\";   //输出到工程的该目录下
//        try {
//            FileOutputStream outputStream = new FileOutputStream(path + name + ".class");
//            outputStream.write(classByete);
//            outputStream.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
