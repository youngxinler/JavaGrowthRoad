package com.graphic.activeObject;

public abstract class MethodRequest<T> {
    protected final Servant servant;
    protected final FutureResult<T> futureResult;

    protected MethodRequest(Servant servant, FutureResult<T> futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
