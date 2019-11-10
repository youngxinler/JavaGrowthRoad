package com.redis.lock;

import redis.clients.jedis.Jedis;

public class RedisLock implements Lock {
    private Jedis jedis;

    public RedisLock(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean lock(String key) {
        return jedis.set(key, "", "nx", "ex", 5L) != null;
    }

    public boolean unlock(String key) {
        return jedis.del(key) != 0;
    }
}
