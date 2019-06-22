package com.graphic.threadPerMessage;

/**
 * @author youngxinler  19-6-2 上午10:20
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        Host host = new Host();
        try {
            host.request(50, 'A');
            host.request(50, 'B');
            host.request(50, 'C');
        } finally {
            System.out.println("main end");
        }

        //theadFactory
//        Host host = new Host(new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable runnable) {
//                return new Thread(runnable);
//            }
//        });

        //executor 接口
//        Host host = new Host(new Executor() {
//            @Override
//            public void execute(Runnable runnable) {
//                new Thread(runnable).start();
//            }
//        });

        //executorService接口
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Host host = new Host(executorService);

        //scheduledExecutorService 类
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//        Host host = new Host(scheduledExecutorService);
//        try {
//            host.request(50, 'A');
//            host.request(50, 'B');
//            host.request(50, 'C');
//        } finally {
//            scheduledExecutorService.shutdown();
//            System.out.println("main end");
//            System.out.println(scheduledExecutorService.isShutdown());
//        }
    }
}
