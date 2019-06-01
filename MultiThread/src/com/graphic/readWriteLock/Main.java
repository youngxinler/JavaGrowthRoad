package com.graphic.readWriteLock;

/**
 * @author youngxinler  19-6-1 下午8:15
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        Data data = new Data(10);
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new WriterThread(data, "ABCDEFG").start();
        new WriterThread(data, "abcdefg").start();
    }
}
