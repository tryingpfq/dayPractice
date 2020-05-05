package com.tryingpfq.classloader;

import java.io.*;

/**
 * @author tryingpfq
 * @date 2020/5/5
 **/
public class FileClassLoader extends ClassLoader {
    private String rootDir;

    public FileClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = tobyteData(name);
        if (data == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, data, 0, data.length);
    }

    private byte[] tobyteData(String name) {
        String path = rootDir + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            // 读取类文件的字节码
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        FileClassLoader loader = new FileClassLoader("C:\\D\\sources\\autosource");
        /**
         * 这里不是很理解 需要这个类的全路径名
         */
        Class<?> customDemo = loader.loadClass("com.tryingpfq.classloader.CustomDemo");
        System.err.println(customDemo.getSimpleName());
    }
}
