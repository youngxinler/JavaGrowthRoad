package com.server.nioHttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioHttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8080));
        server.configureBlocking(false);
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sk = it.next();

                if (sk.isAcceptable()) {
                    SocketChannel sc = ((ServerSocketChannel) sk.channel()).accept();
                    sc.configureBlocking(false);
                    sc.register(sk.selector(), SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    handleRead(sk);
                } else if (sk.isWritable()) {
                    handleWrite(sk);
                }
                it.remove();
            }
        }
    }

    private static void handleRead(SelectionKey sk) throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        while (sc.read(bf) > 0) {
            bf.flip();
            System.out.println("1" + StandardCharsets.UTF_8.decode(bf).toString());
            bf.clear();
        }
        sc.register(sk.selector(), SelectionKey.OP_WRITE);
    }

    private static void handleWrite(SelectionKey sk) throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();
        ByteBuffer bf;
        String content = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=UTF-8\r\nConnection: close\r\n\r\n " +
                " <html><title>snet</title><body>hello</body></html>";
        bf = ByteBuffer.wrap(content.getBytes());
        sc.write(bf);
        sc.close();
        System.out.println("已经成功处理请求!");
    }
}
