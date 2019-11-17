package com.server.simpleHttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    private final int PORT;
    private final String WEBROOT;

    public static final String RIGHT_HEADER = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=UTF-8\r\nConnection: close\r\n\r\n";
    public static final String ERROR_HEADER = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html;charset=UTF-8\r\nConnection: Close\r\n\r\n";
    public static final String NOTFOUND_CONTENT = "<html><head><title>Blast</title></head> <body> Sorry, 404 Not Found </body> </html>";

    private final ServerSocket server;

    public SimpleHttpServer(int PORT, String WEBROOT) throws IOException {
        this.PORT = PORT;
        this.WEBROOT = WEBROOT;
        server = new ServerSocket(PORT);
    }

    public void start() throws IOException {
        while (true) {
            Socket socket = server.accept();
            System.out.println("得到一个来自 " + socket.getRemoteSocketAddress() + " 的连接");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String bf;
            StringBuilder sb = new StringBuilder();

            while (br.ready() && (bf = br.readLine()) != null) {
                sb.append(bf);
            }
            System.out.println("req " + sb.toString());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            try {
                String filePath = getUri(sb.toString());
                if (filePath == null) {
                    bw.write(ERROR_HEADER);
                    bw.write(NOTFOUND_CONTENT);
                } else {
                    String content = getFile(filePath);
                    bw.write(RIGHT_HEADER);
                    bw.write(content);
                }
            } catch (IOException | StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                bw.write(ERROR_HEADER);
                bw.write(NOTFOUND_CONTENT);
            }
            bw.flush();
            br.close();
            bw.close();
            socket.close();
        }
    }


    private String getUri(String firstLine) {
        if (firstLine == null || firstLine.length() == 0) {
            return null;
        }
        int i = firstLine.indexOf(" ");
        int j = firstLine.indexOf(" ", i + 1);
        return firstLine.substring(i + 1, j);
    }

    private String getFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(WEBROOT + filePath)))) {
            StringBuilder sb = new StringBuilder();
            String buf = null;
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            br.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException();
        }
    }
}
