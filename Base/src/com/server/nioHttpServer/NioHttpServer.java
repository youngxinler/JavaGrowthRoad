package com.server.nioHttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NioHttpServer {
    private Executor executor =
            new ThreadPoolExecutor(4, 4, 60L, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(200));

    private static final int PORT = 6666;

    public void start() throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(PORT));
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                executor.execute(new HttpHandler(key));
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NioHttpServer().start();
    }
}
