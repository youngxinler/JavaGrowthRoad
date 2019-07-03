package com.graphic.activeObject.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author youngxinler  19-7-3 下午4:43
 **/

public class ActiveObjectImpl implements ActiveObject {
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    public Future<String> makeString(final int count, final char fillChar) {
        class MakeStringRequest implements Callable<String> {
            public String call() {
                char[] buffer = new char[count];
                for (int i = 0; i < count; i++) {
                    buffer[i] = fillChar;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
                return new String(buffer);
            }
        }
        return service.submit(new MakeStringRequest());
    }

    @Override
    public void displayString(final String string) {
        class DisplayStringRequest implements Runnable {
            @Override
            public void run() {
                try {
                    System.out.println("display: " + string);
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
        service.submit(new DisplayStringRequest());
    }


    @Override
    public void shutdown() {
        service.shutdown();
    }
}
