package com.graphic.guardedSuspension;

/**
 * @author youngxinler  19-5-19 下午9:05
 * @version 0.1
 **/

public class Main {
    private static final String url = "";
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread("alice", 5451L, requestQueue);
        new ServerThread("bob", 1651531L, requestQueue);
    }
}
