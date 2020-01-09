package com.tryingpfq.asm;


import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class ClassGenerator {

    public static void main(String[] args) {

    }


    @Test
    public void generator(){
        // 生成一个类只需要ClassWriter组件即可
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        // 通过visit方法确定类的头部信息
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC+Opcodes.ACC_SUPER,
                "com/tryingpfq/asm/Comparable", null, "java/lang/Object", new String[] {"com/tryingpfq/asm/Mesurable"});
        // 定义类的属性
        cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_FINAL+Opcodes.ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_FINAL+Opcodes.ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_FINAL+Opcodes.ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        // 定义类的方法
        cw.visitMethod(Opcodes.ACC_PUBLIC, "compareTo", "(Ljava/lang/Object;)I",
                null, null).visitEnd();
        cw.visitEnd(); // 使cw类已经完成
        // 将cw转换成字节数组写到文件里面去
        byte[] data = cw.toByteArray();
        File file = new File("out/Comparable.class");
        if (file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fout = new FileOutputStream(file)) {
            fout.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 动态加载生成的class字节码
        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClass("com.tryingpfq.asm.Comparable", data);
        System.out.println(c.getName());
        Field f[] = c.getFields();
        for (int i = 0; i < f.length; i++)
            System.out.println(f[i].toString());
    }

    class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }
}
