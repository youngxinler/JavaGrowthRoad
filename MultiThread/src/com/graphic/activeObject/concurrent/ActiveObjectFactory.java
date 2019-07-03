package com.graphic.activeObject.concurrent;

/**
 * @author youngxinler  19-7-3 下午4:43
 **/

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        return new ActiveObjectImpl();
    }
}
