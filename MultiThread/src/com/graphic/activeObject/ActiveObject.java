package com.graphic.activeObject;

/**
 * @author youngxinler  19-6-29 上午11:13
 **/

public interface ActiveObject {
    Result<String> makeString(int count, char fillChar);

    void displayString(String string);
}
