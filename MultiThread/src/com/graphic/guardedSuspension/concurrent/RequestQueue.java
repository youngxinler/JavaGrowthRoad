package com.graphic.guardedSuspension.concurrent;

import com.graphic.guardedSuspension.Request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author youngxinler  2019/7/4 21:32
 * 使用线程安全的LinkedBlockingQueue实现的RequestQueue.
 **/
public class RequestQueue {
    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Request take() {
        Request req = null;
        try {
            req = queue.take();
        } catch (InterruptedException e) {

        }
        return req;
    }

    public void putRequest(Request request) {
        try {
            queue.put(request);
        } catch (InterruptedException e) {

        }
    }
}
