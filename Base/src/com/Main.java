package com;

import com.redis.lock.Lock;
import com.redis.lock.RedisLock;
import redis.clients.jedis.Jedis;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author youngxinler  19-6-20 下午3:55
 * @version 0.1
 **/

public class Main {

    public static int incrNum = 0;



    public static void main(String[] args) throws Exception{
        Count count = new Count();
        funb(100,count);
        Thread.sleep(1000);
        System.out.println(count.getNum());
    }


    private static void func(int nThread, RedisLock redisLock){
        for (int i = 0; i < nThread; i++){
            new RedisLockClient(redisLock).start();
        }
    }

    private static void funb(int nThread, Count count){
        for (int i = 0; i < nThread; i++) {
            new NormalClient(count).start();
        }
    }
}

class RedisLockClient extends Thread{
    private Lock redisLock;
    private final String key ="只能串行执行";


    public RedisLockClient(Lock redisLock) {
        this.redisLock = new RedisLock(new Jedis("47.93.50.200"));
    }

    @Override
    public void run() {
        boolean isLock = redisLock.lock(key);
        while (!isLock){
            isLock = redisLock.lock(key);
        }
        try {
            System.out.println("do something");
            Main.incrNum += 1;
            System.out.println(Main.incrNum);
            Thread.sleep(0);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            redisLock.unlock(key);
        }
    }
}

class NormalClient extends Thread{

    private Count count;

    public NormalClient(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        int num = count.getNum();
        count.setNum(num + 1);
        System.out.println(count.getNum());
    }
}

class Count {
    private int num;

    public int getNum(){
        return this.num;
    }

    public void setNum(int target){
        this.num = target;
    }
}


