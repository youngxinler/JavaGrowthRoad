package com.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author youngxinler  2019/7/2 11:05
 * <p>
 * 信号量semaphore
 * 1.semaphore初始化的时候, 控制着指定数量的许可.
 * 2.acquire()获取一个许可, 如果没有许可将进行阻塞等待.
 * 3.release()释放一个许可.
 * 4.semaphore并没有确切的锁的对象, 仅仅依靠许可数量的进行控制.
 **/
class Worker implements Runnable {
    private final Semaphore semaphore;
    private final String name;

    public Worker(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("door is using by " + name);
            Thread.sleep((long) Math.random() * 100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}

public class MySemaphore {
    private static ExecutorService service = Executors.newFixedThreadPool(5);
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.execute(new Worker(semaphore, String.valueOf(i)));
        }
        service.shutdown();
    }
}
