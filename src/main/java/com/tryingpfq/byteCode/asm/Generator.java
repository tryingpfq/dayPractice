package com.tryingpfq.byteCode.asm;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author tryingpfq
 * @date 2020/4/3
 **/
public class Generator {

    public void generator(String path){
        try {
            ClassReader reader = new ClassReader(path);
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassVisitor classVisitor = new MyClassVisitor(writer);
            reader.accept(classVisitor, ClassReader.SKIP_DEBUG);
            byte[] data = writer.toByteArray();
            File f = new File("target/classes/com/tryingpfq/asm/Base.class");
            FileOutputStream fout = new FileOutputStream(f);
            fout.write(data);
            fout.close();
            System.out.println("now generator cc success!!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyClassVisitor extends ClassAdapter implements Opcodes {

        public MyClassVisitor(org.objectweb.asm.ClassVisitor cv) {
            super(cv);
        }

        @Override
        public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
            super.visit(i, i1, s, s1, s2, strings);
        }

        @Override
        public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
            MethodVisitor mv = cv.visitMethod(i, s, s1, s2,
                    strings);
            //Base类中有两个方法：无参构造以及process方法，这里不增强构造方法
            if (!s.equals("<init>") && mv != null) {
                mv = new MyMethodVisitor(mv);
            }
            return mv;
        }

    }

    class MyMethodVisitor extends MethodAdapter implements Opcodes {
        public MyMethodVisitor(MethodVisitor mv) {
            super(mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("start");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }
        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                    || opcode == Opcodes.ATHROW) {
                //方法在返回之前，打印"end"
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("end");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            }
            mv.visitInsn(opcode);
        }

    }
}
