package com.graphic.activeObject;

public class Proxy implements ActiveObject {
    private final SchedulerThread schedulerThread;
    private final Servant servant;

    public Proxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result<String> makeString(int count, char fillChar) {
        FutureResult<String> future = new FutureResult<String>();
        schedulerThread.invoke(new MakeStringRequest(servant, future, count, fillChar));
        return future;
    }

    @Override
    public void displayString(String string) {
        schedulerThread.invoke(new DisplayStringRequest(servant, string));
    }
}
