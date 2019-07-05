package com.graphic.workerThread.concurrent;

/**
 * @author youngxinler  19-7-5 下午2:06
 * 实现了Runnable接口的Request
 **/

public class Request implements Runnable {
    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public String toString() {
        return "[ request from + " + name + " No. " + number + " ]";
    }
}
