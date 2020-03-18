package com.tryingpfq.serialize;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tryingpfq
 * @date 2020/3/17
 **/
public class Info {
    private int id;

    private String firstName;

    private Map<Integer, String> map;

    private transient String other;

    public static Info valueOf() {
        Info info = new Info();
        info.id = 1;
        info.firstName = "aaa";
        Map<Integer, String> map = new HashMap<>();
        map.put(1111, "abc");
        info.map = map;
        info.other = "other";
        return info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", map=" + map +
                ", other='" + other + '\'' +
                '}';
    }
}
