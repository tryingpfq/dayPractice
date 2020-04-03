package com.tryingpfq.byteCode.jssist;

import com.tryingpfq.byteCode.Base;
import javassist.*;

import java.io.IOException;

/**
 * @author tryingpfq
 * @date 2020/4/3
 **/
public class JavassistDemo {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, IllegalAccessException, InstantiationException {
        Base base = new Base();
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.getCtClass("com.tryingpfq.byteCode.Base");
        CtMethod ctMethod = ctClass.getDeclaredMethod("process");
        ctMethod.insertBefore("{ System.out.println(\"start\"); }");
        ctMethod.insertAfter("{ System.out.println(\"end\"); }");
        Class c = ctClass.toClass();
        ctClass.writeFile("out");
        Base h = (Base)c.newInstance();
        h.process();
    }
}
