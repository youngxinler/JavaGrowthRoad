package com.server.nioHttpServer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class HttpHandler implements Runnable {
    private static final int BUFFER_SIZE = 1024;
    private static final String CHARSET = "UTF-8";
    private SelectionKey key;

    public static final String RIGHT_HEADER = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=UTF-8\r\nConnection: close\r\n\r\n";
    public static final String ERROR_HEADER = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html;charset=UTF-8\r\nConnection: Close\r\n\r\n";
    public static final String NOTFOUND_CONTENT = "<html><head><title>Blast</title></head> <body> Sorry, 404 Not Found </body> </html>";


    public HttpHandler(SelectionKey selectionKey) {
        this.key = selectionKey;
    }


    private void handleAccept() throws IOException {
        SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ);
    }

    private void handleRead() throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
        StringBuilder sb = new StringBuilder();
        while (sc.read(buf) != -1) {
            buf.flip();
            sb.append(Charset.forName(CHARSET).newDecoder().decode(buf).toString());
            buf.clear();
        }
        String[] strs = sb.toString().split("\r\n");
        //设置回复内容
        StringBuilder res = new StringBuilder();
        res.append(RIGHT_HEADER);
        res.append("<html><title>snet</title><body>");
        for (String s : strs) {
            res.append(s).append("<br/>");
            System.out.println(s);
        }
        res.append("</body></html>");
        buf = ByteBuffer.wrap(res.toString().getBytes(CHARSET));
        //写入回复
        sc.write(buf);
        sc.close();
    }

    @Override
    public void run() {
        try {
            if (key.isAcceptable()) {
                handleAccept();
            } else if (key.isReadable()) {
                handleRead();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
