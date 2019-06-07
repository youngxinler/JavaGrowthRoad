package com.graphic.future;

public class FutureData implements Data {
    private boolean ready = false;
    private RealData realData;

    public synchronized void setContent(RealData realData) {
        if (ready) {
            return;
        }
        this.realData = realData;
        ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
