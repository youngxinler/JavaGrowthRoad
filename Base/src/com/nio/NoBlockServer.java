package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class NoBlockServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();

        server.configureBlocking(false);
        server.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();

        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\youngxinler\\Desktop\\ppppp.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                    while (client.read(buf) > 0) {
                        buf.flip();
                        outChannel.write(buf);
                        buf.clear();
                    }
                    client.close();
                }
                iterator.remove();
            }
        }
        server.close();
    }
}
