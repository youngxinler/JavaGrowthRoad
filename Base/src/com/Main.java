package com;

import com.server.multiBlockHttpServer.MultiBlockHttpServer;
import com.server.simpleHttpServer.SimpleHttpServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        mStart();
    }

    private static void mStart() throws IOException {
        MultiBlockHttpServer server = new MultiBlockHttpServer(8080, 5, "C:\\Users\\youngxinler\\Desktop");
        server.start();
    }

    private static void sStart() throws IOException {
        SimpleHttpServer server = new SimpleHttpServer(8080, "C:\\Users\\youngxinler\\Desktop");
        server.start();
    }
}