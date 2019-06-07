package com.graphic.future;

public class Host {
    public Data request(final int count, final char c) {
        System.out.println("    request(" + count + "," + c + ") BEGIN");
        final FutureData futureData = new FutureData();

        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(count, c);
                futureData.setContent(realData);
            }
        }.start();
        return futureData;
    }
}
