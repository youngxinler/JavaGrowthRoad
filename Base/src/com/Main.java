package com;

import redis.clients.jedis.Jedis;

/**
 * @author youngxinler  19-6-20 下午3:55
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) throws Exception{
        Jedis jedis = new Jedis("47.93.50.200");
        String res = jedis.set("hello", "", "nx", "ex", 5L);
        Thread.sleep(3);
        String res2 = jedis.set("hello", "", "nx", "ex", 5L);
        System.out.println(res);
        System.out.println(res2);
        System.out.println(jedis.del("world"));
    }
}



