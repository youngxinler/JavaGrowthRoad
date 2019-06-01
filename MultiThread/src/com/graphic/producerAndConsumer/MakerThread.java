package com.graphic.producerAndConsumer;

import java.util.Random;

/**
 * @author youngxinler  19-6-1 上午11:40
 * @version 0.1
 **/

public class MakerThread extends Thread{
    private final Random random;
    private final Table table;
    private static int id = 0;

    public MakerThread(String name, Table table, long seed) {
        super(name);
        this.random = new Random(seed);
        this.table = table;
    }

    @Override
    public void run(){
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                String cake = "[Cake No." + nextId() + " by " + getName() + "]";
                table.put(cake);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private int nextId(){
        return id++;
    }
}
