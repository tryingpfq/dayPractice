package com.tryingpfq.serialize.javaserialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author tryingpfq
 * @Date 2020/4/11
 */
public class Demo  {

    public static void main(String[] args) {
       // readObj();
        json();
    }

    public static void writeObj() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            SerializeObj obj = SerializeObj.INSTANCE;
            objectOutputStream.writeObject(obj);
        } catch (Exception e) {
        };
    }

    public static void readObj(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.txt"))) {
            SerializeObj o = (SerializeObj)inputStream.readObject();
            System.err.println(SerializeObj.INSTANCE);
            System.err.println(o);
            if (o == SerializeObj.INSTANCE) {
                System.err.println("true");
            }
        } catch (Exception e) {

        }
    }

    public static void json(){
        String o = JSON.toJSONString(SerializeObj.INSTANCE);
        SerializeObj obj = JSONObject.parseObject(o, SerializeObj.class);
        System.err.println(obj);
        System.err.println(SerializeObj.INSTANCE);
    }
}
