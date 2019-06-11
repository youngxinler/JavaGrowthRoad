package com.graphic.activeObject;

public interface ActiveObject {
    Result<String> makeString(int count, char fillChar);

    void displayString(String string);
}
