package com.graphic.workerThread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author youngxinler  19-7-5 下午2:12
 **/

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {
            new ClientThread("alice", executorService).start();
            new ClientThread("bobby", executorService).start();
            new ClientThread("chris", executorService).start();
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        } finally {
            executorService.shutdown();
        }
    }
}
