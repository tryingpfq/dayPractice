package com.tryingpfq.redis;

import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisLock {

    public String getLock(String key){
        try {
            Jedis jedis = RedisManager.getJedisPool();
            String value = UUID.randomUUID().toString();

            if (jedis.setnx(key, value) == 1) {
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean releaseLock() {
        return false;
    }
}
