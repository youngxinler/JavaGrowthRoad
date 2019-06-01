package com.graphic.producerAndConsumer;

import java.util.Random;

/**
 * @author youngxinler  19-6-1 下午12:44
 * @version 0.1
 **/

public class ConsumerThread extends Thread{
    private final Table table;
    private final Random random;

    public ConsumerThread(String s, Table table, long seed) {
        super(s);
        this.table = table;
        this.random = new Random(seed);
    }

    @Override
    public void run(){
        try{
            while (true){
                Thread.sleep(random.nextInt(1000));
                String cake = table.take();
                System.out.println(cake + " is eaten by " + getName());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
