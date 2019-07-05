package com.graphic.workerThread.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author youngxinler  19-7-5 下午2:10
 **/

public class ClientThread extends Thread {
    private final ExecutorService service;
    private static final Random random = new Random();

    public ClientThread(String s, ExecutorService service) {
        super(s);
        this.service = service;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                service.execute(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException | RejectedExecutionException e) {
            e.printStackTrace();
        }
    }
}
