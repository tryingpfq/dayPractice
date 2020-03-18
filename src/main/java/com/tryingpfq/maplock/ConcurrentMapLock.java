package com.tryingpfq.maplock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tryingpfq
 * @date 2020/2/26
 * jdk 1.8 concurrentHashMap 死锁
 **/
public class ConcurrentMapLock {
    private static Map<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        map.computeIfAbsent("AaAa", k -> {
            return  map.put("BBBB",42);
        });
        System.out.println(map);
    }
}
