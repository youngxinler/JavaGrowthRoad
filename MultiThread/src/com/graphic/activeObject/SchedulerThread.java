package com.graphic.activeObject;

/**
 * @author youngxinler  19-6-29 上午11:51
 **/

public class SchedulerThread extends Thread {
    private final ActivationQueue queue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.queue = activationQueue;
    }

    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }

    public void run() {
        while (true) {
            MethodRequest request = queue.takeRequest();
            request.execute();
        }
    }
}
