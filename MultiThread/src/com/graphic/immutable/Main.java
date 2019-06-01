package com.graphic.immutable;

/**
 * @author youngxinler  19-5-19 下午8:25
 * @version 0.1
 **/

public class Main {
    public static void main(String[] args) {
        Person alice = new Person("Alice", "Alaska");
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
    }
}
