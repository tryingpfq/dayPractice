package com.tryingpfq.serialize.javaserialize;

import java.io.Serializable;

/**
 * @Author tryingpfq
 * @Date 2020/4/11
 */
public class SerializeObj implements Serializable {
    private static final long serialVersionUID = -6597206475408910605L;

    public static SerializeObj INSTANCE = new SerializeObj();

    private int id;

    private String name;

    public static SerializeObj valueOf(int id, String name) {
        SerializeObj obj = new SerializeObj();
        obj.setId(id);
        obj.setName(name);
        return obj;
    }

    private Object readResolve(){
        return INSTANCE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
