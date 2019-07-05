package com.graphic.threadPerMessage.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author youngxinler  19-7-5 下午1:51
 **/

public class Main {
    public static void main(String[] args) {
        System.out.println("main begin");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        Host host = new Host(scheduledExecutorService);
        try {
            host.request(10, 'A');
            host.request(20, 'B');
            host.request(30, 'C');
        } finally {
            scheduledExecutorService.shutdown();
            System.out.println("main end");
        }
    }
}
