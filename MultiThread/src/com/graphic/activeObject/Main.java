package com.graphic.activeObject;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("alice", activeObject).start();
        new MakerClientThread("boddy", activeObject).start();
        new DisplayClientThread("chris", activeObject).start();
    }
}
