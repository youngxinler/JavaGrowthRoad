package com.graphic.activeObject;

/**
 * @author youngxinler  19-6-29 上午11:18
 **/

abstract class MethodRequest<T> {
    protected final Servant servant;
    protected final FutureResult<T> future;

    protected MethodRequest(Servant servant, FutureResult<T> future) {
        this.future = future;
        this.servant = servant;
    }

    public abstract void execute();
}
