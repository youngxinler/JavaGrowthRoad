package com.graphic.readWriteLock;

/**
 * @author youngxinler  19-6-1 下午7:49
 * @version 0.1
 **/

public final class ReadWriteLock {
    private int readingReaders = 0;
    private int waitingWriters = 0;
    private int writingWriters = 0;
    private boolean preferWriter = true;

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (waitingWriters > 0 && preferWriter)) {
            wait();
        }
        notifyAll();
    }

    public synchronized void readUnlock() {
        readingReaders--;
        preferWriter = true;
        notifyAll();
    }

    //finally 确保
    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;
        }
        writingWriters++;
    }

    public synchronized void writeUnlock() {
        writingWriters--;
        preferWriter = false;
        notifyAll();
    }
}
