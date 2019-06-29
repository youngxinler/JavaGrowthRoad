package com.graphic.activeObject;

/**
 * @author youngxinler  19-6-29 上午11:09
 **/

public class RealResult<T> extends Result<T> {
    private final T resultValue;

    public RealResult(T resultValue) {
        this.resultValue = resultValue;
    }


    @Override
    public T getResultValue() {
        return resultValue;
    }
}
