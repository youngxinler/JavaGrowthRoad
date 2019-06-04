package com.graphic.twoPhaseTermination;

/**
 * @author youngxinler  19-6-4 下午5:47
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        System.out.println("main : start");
        try {
            CountUpThread countUpThread = new CountUpThread();
            countUpThread.start();

            Thread.sleep(10000);
            System.out.println("main : shutdownRequest");
            countUpThread.shutdown();


            System.out.println("main : join");
            countUpThread.join();
            System.out.println("main check counterThread isInterrupt" + countUpThread.isShutdownRequested());
            while (!countUpThread.isShutdownRequested()) {
                Thread.sleep(1000);
                System.out.println("no interrupt");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main : end");
    }
}
