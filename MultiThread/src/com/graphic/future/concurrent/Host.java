package com.graphic.future.concurrent;

import com.graphic.future.RealData;

/**
 * @author youngxinler  19-7-5 下午2:29
 **/

public class Host {
    public FutureData request(final int count, final char c) {
        System.out.println("    request(" + count + "," + c + ") BEGIN");

        FutureData future = new FutureData(
                () -> {
                    return new RealData(count, c);
                }
        );
        new Thread(future).start();
        System.out.println("    request(" + count + "," + c + ") END");
        return future;
    }
}
