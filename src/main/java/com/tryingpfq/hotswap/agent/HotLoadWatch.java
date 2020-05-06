package com.tryingpfq.hotswap.agent;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.classfile.ClassFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.util.jar.JarFile;

/**
 * @author tryingpfq
 * @date 2020/5/6
 **/
public class HotLoadWatch {

    private static final String GAGNET_JAR_Name = "common-hotswap-agent";

    private static final String agent_Class_Path = "com.tryingpfq.GAgent";

    private String agentJarPath;

    private Instrumentation ist;
    private HotLoadWatch(String agentJarPath) {
        this.agentJarPath = agentJarPath;
        tryInitIst();
    }

    public static void start(String agentJarPath, String watchPath) {
        //获取lib agentLib不存在的时候就不启动热更
        HotLoadWatch hotLoadWatch = new HotLoadWatch("");

        //要监听的文件
        File file = new File(watchPath);

        if (!file.exists()) {
            throw new RuntimeException("watch path is null,Path:" + watchPath);
        }
        //文件变更监听
        FileAlterationObserver observer = new FileAlterationObserver(file);
        observer.addListener(new FileAlterationListenerAdaptor(){
            @Override
            public void onStart(FileAlterationObserver observer) {

            }

            @Override
            public void onStop(FileAlterationObserver observer) {

            }

            @Override
            public void onFileChange(File file) {

            }

            @Override
            public void onFileCreate(File file) {

            }
        });

        FileAlterationMonitor monitor = new FileAlterationMonitor(5000);
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hotLoad(File file) {
        if (ist == null) {
            VirtualMachine virtualMachine = null;
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            try {
                virtualMachine = VirtualMachine.attach(pid);
                virtualMachine.loadAgent(agentJarPath,null);
                tryInitIst();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String extension = FilenameUtils.getExtension(file.getName();
        if ("class".equals(extension)) {
            loadClass(file);
        }else if("jar".equals(extension)){
            loadJarFile(file);
        }
    }

    private void loadClass(File file) {
        try {
            byte[] classFileByte = FileUtils.readFileToByteArray(file);
            ClassFile classFile = ClassFile.read(file);
            String className = classFile.getName().replaceAll("/", ".");
            Class clazz = Class.forName(className);

            //重新定义
            ClassDefinition classDefinition = new ClassDefinition(clazz, classFileByte);
            ist.redefineClasses(classDefinition);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadJarFile(File file)  {
        String absolutePath = file.getAbsolutePath();
        try {
            ist.appendToSystemClassLoaderSearch(new JarFile(absolutePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tryInitIst() {
        try {
            Class<?> g_agentClass = Class.forName(agent_Class_Path);
            Field ist_field = g_agentClass.getDeclaredField("ist");
            this.ist = (Instrumentation) ist_field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
