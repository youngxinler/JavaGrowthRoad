package com.graphic.activeObject;

/**
 * @author youngxinler  19-6-29 下午12:02
 **/

public class MakerClientThread extends Thread {
    private final ActiveObject activeObject;
    private final char fillchar;

    public MakerClientThread(String s, ActiveObject activeObject) {
        super(s);
        this.activeObject = activeObject;
        this.fillchar = s.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Result<String> result = activeObject.makeString(i, fillchar);
                Thread.sleep(10);
                String value = result.getResultValue();
                System.out.println(Thread.currentThread().getName() + ": value = " + value);
            }
        } catch (InterruptedException e) {

        }
    }
}
