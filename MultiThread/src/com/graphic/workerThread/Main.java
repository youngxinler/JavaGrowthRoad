package com.graphic.workerThread;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();
        new ClientThread("alice", channel).start();
        new ClientThread("bob", channel).start();
        new ClientThread("blank", channel).start();
    }
}
