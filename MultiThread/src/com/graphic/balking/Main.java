package com.graphic.balking;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("balkingTest", "this si first");
        new ChangerThread("changer-1", data).start();
        new SaverThread("saver-1", data).start();
    }
}
