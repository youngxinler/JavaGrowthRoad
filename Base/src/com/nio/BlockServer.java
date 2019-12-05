package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\youngxinler\\Desktop\\ppppp.txt"), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);

        server.bind(new InetSocketAddress(6666));

        SocketChannel socketChannel = server.accept();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (socketChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        outChannel.close();
        socketChannel.close();
        server.close();
    }
}
