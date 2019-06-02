package com.graphic.readWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author youngxinler  19-6-1 下午7:49
 * @version 0.1
 **/

//public class Data {
//    private final char[] buffers;
//    private final ReadWriteLock lock = new ReadWriteLock();
//
//    public Data(int size) {
//        this.buffers = new char[size];
//        for (int i = 0; i < buffers.length; i++) {
//            buffers[i] = '*';
//        }
//    }
//
//    public char[] read() throws InterruptedException {
//        lock.readLock();
//        try {
//            return doRead();
//        } finally {
//            lock.readUnlock();
//        }
//    }
//
//    public void write(char c) throws InterruptedException {
//        lock.writeLock();
//        try {
//            doWrite(c);
//        } finally {
//            lock.writeUnlock();
//        }
//    }
//
//    private void doWrite(char c) {
//        for (int i = 0; i < buffers.length; i++) {
//            buffers[i] = c;
//            slowly();
//        }
//    }
//
//
//    private char[] doRead() {
//        char[] newbuf = new char[buffers.length];
//        for (int i = 0; i < buffers.length; i++) {
//            newbuf[i] = buffers[i];
//        }
//        slowly();
//        return newbuf;
//    }
//
//    private void slowly() {
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}


//使用java.util.concurrent.ReentrantReadWriteLock;
public class Data {
    private final char[] buffer;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);//fair!
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() {
        readLock.lock();
        try {
            return doRead();
        } finally {
            readLock.unlock();
        }
    }

    public void write(char c) {
        writeLock.lock();
        try {
            doWrite(c);
        } finally {
            writeLock.unlock();
        }
    }

    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newbuf[i] = buffer[i];
        }
        slowly();
        return newbuf;
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}