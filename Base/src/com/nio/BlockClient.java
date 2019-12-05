package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));

        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\youngxinler\\Desktop\\test.txt"), StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (fileChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        fileChannel.close();
        socketChannel.close();
    }
}
