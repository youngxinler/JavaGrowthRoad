package com.graphic.future.concurrent;

import com.graphic.future.Data;
import com.graphic.future.RealData;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author youngxinler  19-7-5 下午2:29
 **/

public class FutureData extends FutureTask<RealData> implements Data {
    public FutureData(Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public String getContent() {
        String string = null;
        try {
            string = get().getContent();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return string;
    }
}
