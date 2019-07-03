package com.graphic.activeObject.concurrent;

import java.util.concurrent.Future;

/**
 * @author youngxinler  19-7-3 下午4:39
 **/

public interface ActiveObject {
    Future<String> makeString(int count, char fillChar);

    void displayString(String string);

    void shutdown();
}
