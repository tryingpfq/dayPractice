//package com.tryingpfq.byteCode.asm;
//
//import org.objectweb.asm.*;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class ASMHelper implements Opcodes {
//
//    static class MyMethodVisitor extends MethodVisitor{
//
//        public MyMethodVisitor(int api, MethodVisitor methodVisitor) {
//            super(api, null);
//            this.mv = methodVisitor;
//        }
//
//        @Override
//        public void visitCode() {
//            mv.visitCode();
//            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            mv.visitLdcInsn("hello world");
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//            mv.visitInsn(RETURN);
//            mv.visitMaxs(2, 1);
//            mv.visitEnd();
//        }
//    }
//
//
//    static class MyClassVisitor extends ClassVisitor {
//
//        public MyClassVisitor(int api, ClassVisitor cv) {
//            super(api, cv);
//        }
//
//        @Override
//        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
//                                         String[] exceptions) {
//            MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
//            if ("main".equals(name)) {
//                return new MyMethodVisitor(ASM7, visitor);
//            }
//            return visitor;
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        ClassReader cr = new ClassReader("Foo");
//        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//        ClassVisitor cv = new MyClassVisitor(ASM7, cw);
//        cr.accept(cv, ClassReader.SKIP_FRAMES);
//        Files.write(Paths.get("Foo.class"), cw.toByteArray());
//    }
//}
