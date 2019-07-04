package com.graphic.producerAndConsumer.concurrent;

import java.util.concurrent.ArrayBlockingQueue;


/**
 * @author youngxinler  2019/7/4 22:14
 **/
public class Table extends ArrayBlockingQueue<String> {
    public Table(int count) {
        super(count);
    }

    @Override
    public void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        super.put(cake);
    }

    @Override
    public String take() throws InterruptedException {
        String cake = super.take();
        System.out.println(Thread.currentThread().getName() + " take " + cake);
        return cake;
    }
}
