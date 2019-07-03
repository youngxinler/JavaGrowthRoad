package com.graphic.activeObject.concurrent;

/**
 * @author youngxinler  19-7-3 下午6:32
 **/

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        try {
            new MakerClientThread("alice", activeObject).start();
            new MakerClientThread("bobby", activeObject).start();
            new DisplayClientThread("chris", activeObject).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
