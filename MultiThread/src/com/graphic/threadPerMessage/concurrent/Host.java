package com.graphic.threadPerMessage.concurrent;

import com.graphic.threadPerMessage.Helper;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author youngxinler  2019/7/5 12:36
 * 使用scheduledExecutorServive接口实现的Host
 **/
public class Host {
    private final Helper helper = new Helper();
    private final ScheduledExecutorService scheduledExecutorService;

    public Host(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void request(final int count, final char c) {
        System.out.println(" request (" + count + ", " + c + ") BEGIN");
        scheduledExecutorService.schedule(
                () -> {
                    helper.handle(count, c);
                },
                3L,
                TimeUnit.SECONDS
        );
        System.out.println("    request(" + count + "," + c + ") END");
    }
}
