package com.graphic.threadPerMessage;

/**
 * @author youngxinler  19-6-2 上午10:09
 * @version 0.1
 **/

public class Helper {
    public void handle(int count, char c) {
        System.out.println("    handle begin " + c);
        for (int i = 0; i < count; i++) {
            slowly();
            System.out.print(c);
        }
        System.out.println();
        System.out.println("    handle end " + c);
    }

    private void slowly() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
