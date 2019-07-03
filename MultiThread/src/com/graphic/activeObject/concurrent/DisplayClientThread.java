package com.graphic.activeObject.concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author youngxinler  19-7-3 下午6:23
 **/

public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                String s = Thread.currentThread().getName() + " " + i;
                activeObject.displayString(s);
                Thread.sleep(200);
            }
        } catch (RejectedExecutionException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        } catch (CancellationException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        }
    }
}
