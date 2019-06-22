package com.graphic.threadPerMessage;

/**
 * @author youngxinler  19-6-2 上午10:17
 * @version 0.1
 **/


public class Host {
    private final Helper helper = new Helper();

    public Host() {
    }

    //如果内部类使用了方法参数, 那么必须对参数加上final, 否则会报错
    public void request(final int count, final char c) {
        System.out.println("request begin " + count + " " + c);

        //  每个request 委托新的线程去执行
        // 适合如果处理的时间特别长, 而且对操作顺序和返回值没有要求
        new Thread() {
            @Override
            public void run() {
                helper.handle(count, c);
            }
        }.start();
        System.out.println("request end " + count + " " + c);
    }
}


//Runnable 接口
//public class Host{
//    private final Helper helper = new Helper();
//
//    public void request(final int count, final char c){
//        System.out.println("request begin " + count + " " + c);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                helper.handle(count, c);
//            }
//        }).start();
//        System.out.println("request end " + count + " " + c);
//    }
//}

//java.util.concurrent.ThreadFactory 接口
//可以控制线程的创建过程
//public class Host{
//    private final Helper helper = new Helper();
//    private final ThreadFactory threadFactory;
//
//    public Host(ThreadFactory threadFactory){
//        this.threadFactory = threadFactory;
//    }
//
//    public void request(final int count, final char c){
//        System.out.println("request begin " + count + " " + c);
//        threadFactory.newThread(new Runnable() {
//            @Override
//            public void run() {
//                helper.handle(count, c);
//            }
//        }).start();
//        System.out.println("request end " + count + " " + c);
//    }
//}

//executor 接口
// 创建线程的过程被隐藏, 控制执行过程
//public class Host{
//    private final Helper helper = new Helper();
//    private final Executor executor;
//
//    public Host(Executor executor){
//        this.executor = executor;
//    }
//
//    public void request(final int count, final char c){
//        System.out.println("request begin " + count + " " + c);
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                helper.handle(count, c);
//            }
//        });
//        System.out.println("request end " + count + " " + c);
//    }
//}

//executorService 接口
//service后由线程池来管理线程的创建, 复用这些线程进行处理
//public class Host{
//    private final Helper helper = new Helper();
//    private final ExecutorService executorService;
//
//    public Host(ExecutorService executorService){
//        this.executorService = executorService;
//    }
//
//    public void request(final int count, final char c){
//        System.out.println("request begin " + count + "" + c);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                helper.handle(count, c);
//            }
//        });
//        System.out.println("request end " + count + " " + c);
//    }
//}

//scheduledExecutorService 接口           调度执行
//对线程的执行时间进行控制, 推迟操作的执行
//public class Host {
//    private final Helper helper = new Helper();
//    private final ScheduledExecutorService scheduledExecutorService;
//
//    public Host(ScheduledExecutorService scheduledExecutorService) {
//        this.scheduledExecutorService = scheduledExecutorService;
//    }
//
//    public void request(final int count, final char c) {
//        System.out.println("request begin " + count + " " + c);
//        scheduledExecutorService.schedule(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        helper.handle(count, c);
//                    }
//                },
//                3L,
//                TimeUnit.SECONDS
//        );
//        System.out.println("request end " + count + " " + c);
//    }
//}

