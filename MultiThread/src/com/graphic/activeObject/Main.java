package com.graphic.activeObject;

/**
 * @author youngxinler  19-6-29 下午12:05
 **/

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("alice", activeObject).start();
        new MakerClientThread("bobby", activeObject).start();
        new DisplayClientThread("chris", activeObject).start();
    }
}
