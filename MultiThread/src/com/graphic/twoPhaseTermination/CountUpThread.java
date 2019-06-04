package com.graphic.twoPhaseTermination;

/**
 * @author youngxinler  19-6-4 下午5:36
 * @version 0.1
 * <p>
 * 这里有一点不懂, 如果去掉shutdownRequested变量, 那么由无论是内部还是外部去调用isInterrupted()方法, 判断线程是否中断的话, isInterrupted()方法的返回结果一直是false.
 * <p>
 * 加上shutdownRequested方法的话, 使用isShutdownRequested()来判断"逻辑上的线程中断"是好使的, 但是为什么isInterrupted()就不行呢? 这难道是个"鬼才方法"? 调用isInterrupted()会自动结束线程的中断状态?
 **/


public class CountUpThread extends Thread {
    private long counter = 0;

    private volatile boolean shutdownRequested = false;

    public void shutdown() {
        shutdownRequested = true;
        interrupt();
    }

    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    @Override
    public void run() {
        try {
            while (true) {
                doWork();
            }
        } catch (InterruptedException e) {
            System.out.println("i get the interruptedException");
            System.out.println("self check counterThread isInterrupt" + Thread.currentThread().isInterrupted());
            interrupt();
        } finally {
            doShutdown();
        }
    }


    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }

    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
}
