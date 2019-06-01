package com.graphic.guardedSuspension;

import java.util.Random;

/**
 * @author youngxinler  19-5-19 下午8:55
 * @version 0.1
 **/

public class ClientThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(String s, long seed, RequestQueue requestQueue) {
        super(s);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("No," + i);
            System.out.println(Thread.currentThread().getName() + " request " + request);
            requestQueue.putRequest(request);
            try {
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
