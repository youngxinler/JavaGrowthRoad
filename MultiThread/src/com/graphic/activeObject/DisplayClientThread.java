package com.graphic.activeObject;

/**
 * @author youngxinler  19-6-29 下午12:00
 **/

public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String s, ActiveObject activeObject) {
        super(s);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                String string = Thread.currentThread().getName() + " " + i;
                activeObject.displayString(string);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {

        }
    }
}
