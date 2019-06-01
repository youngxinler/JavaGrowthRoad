package com.graphic.guardedSuspension;

import java.util.Random;

/**
 * @author youngxinler  19-5-19 下午9:00
 * @version 0.1
 **/

public class ServerThread extends Thread{
    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(String name, long seed, RequestQueue requestQueue){
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " request " + request);
            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
