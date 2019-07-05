package com.graphic.threadPerMessage.concurrent;

import com.graphic.threadPerMessage.Helper;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @author youngxinler  2019/7/5 12:36
 **/
public class Host {
    private final Helper helper = new Helper();
    private final ScheduledExecutorService scheduledExecutorService;

    public Host(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void request(final int count, final char c) {
        System.out.println(" request (" + count + ", " + c + ") BEGIN");
    }
}
