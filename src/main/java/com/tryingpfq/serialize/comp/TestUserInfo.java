package com.tryingpfq.serialize.comp;

import java.io.*;

/**
 * @author tryingpfq
 * @date 2020/6/4
 **/
public class TestUserInfo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserInfo userInfo = UserInfo.valueOf(1, "peng");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(userInfo);
        oos.flush();

        oos.close();

        byte[] b = bos.toByteArray();
        System.out.println("java serialize length " + b.length);
        System.out.println("codec length " + userInfo.codec().length);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(b));
        UserInfo object = (UserInfo) ois.readObject();

        UserInfo object1 = userInfo.decode(userInfo.codec());
        System.out.println();
    }
}
