package com;

import com.server.simpleHttpServer.SimpleHttpServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SimpleHttpServer server = new SimpleHttpServer(8888, "C:\\Users\\youngxinler\\Desktop");
        server.start();
    }
}