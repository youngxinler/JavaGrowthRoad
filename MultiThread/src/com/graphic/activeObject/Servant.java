package com.graphic.activeObject;

public class Servant implements ActiveObject {
    @Override
    public Result<String> makeString(int count, char fillChar) {
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = fillChar;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult<String>(new String(buffer));
    }

    @Override
    public void displayString(String string) {
        try {
            System.out.println("displayString   " + string);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
