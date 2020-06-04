package com.tryingpfq.serialize.comp;

import com.google.inject.internal.cglib.core.$ClassNameReader;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author tryingpfq
 * @date 2020/6/4
 **/
@Getter
@Setter
public class UserInfo implements Serializable {
    private int userId;

    private String name;

    public static UserInfo valueOf(int id, String name) {
        UserInfo info = new UserInfo();
        info.userId = id;
        info.name = name;
        return info;
    }

    public byte[] codec(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] value = name.getBytes();
        byteBuffer.putInt(value.length);
        byteBuffer.put(value);
        byteBuffer.putInt(userId);
        byteBuffer.flip();
        byte[] result = new byte[byteBuffer.remaining()];
        return result;
    }

    public UserInfo decode(byte[] data){
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        int nameLength = byteBuffer.getInt();
        byte[] nameData = new byte[nameLength];
        String name = byteBuffer.get(nameData, 0, nameLength).toString();
        int id = byteBuffer.getInt();
        return UserInfo.valueOf(id, name);
    }
}
