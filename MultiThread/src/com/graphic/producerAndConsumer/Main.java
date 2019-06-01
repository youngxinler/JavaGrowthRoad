package com.graphic.producerAndConsumer;

/**
 * @author youngxinler  19-6-1 下午12:49
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MakerThread("maker-1", table, 1000).start();
        new MakerThread("maker-2", table, 1000).start();
        new MakerThread("maker-3", table, 1000).start();
        new ConsumerThread("consumer-1", table, 1).start();
        new ConsumerThread("consumer-2", table, 2).start();
        new ConsumerThread("consumer-3", table, 3).start();
    }
}
