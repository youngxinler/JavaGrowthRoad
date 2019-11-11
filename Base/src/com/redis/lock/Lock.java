package com.redis.lock;

public interface Lock {
    boolean lock(String key);
    boolean unlock(String key);
}
