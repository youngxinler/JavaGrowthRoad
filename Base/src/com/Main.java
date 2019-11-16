package com;

import com.server.multiBlockHttpServer.MultiBlockHttpServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MultiBlockHttpServer server = new MultiBlockHttpServer(6666, 20);
        server.start();
    }
}