package com.server.multiBlockHttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiBlockHttpServer {
    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private final String WEBROOT;

    public MultiBlockHttpServer(int port, int nThread, String webRoot) throws IOException {
        executorService = Executors.newFixedThreadPool(nThread);
        serverSocket = new ServerSocket(port);
        this.WEBROOT = webRoot;
    }

    public void start() throws IOException {
        System.out.println("Start blast!");
        ConnectionHandler.webRoot = WEBROOT;
        while (true) {
            Socket s = serverSocket.accept();
            System.out.println("来自 " + s.getRemoteSocketAddress() + " 的连接");
            executorService.submit(new ConnectionHandler(s));
        }
    }
}
