package com.graphic.guardedSuspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author youngxinler  19-5-19 下午8:45
 * @version 0.1
 **/

public class RequestQueue {
    private final Queue<Request> requestQueue = new LinkedList<Request>();

    public synchronized Request getRequest(){
        while (requestQueue.peek() == null){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return requestQueue.remove();
    }

    public synchronized void putRequest(Request request){
        requestQueue.add(request);
        notifyAll();
    }
}
