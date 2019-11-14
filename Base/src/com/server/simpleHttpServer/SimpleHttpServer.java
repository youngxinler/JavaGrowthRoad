package com.server.simpleHttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    private final int PORT;
    private final String WEBROOT;

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
            String bf = null;
            StringBuilder sb = new StringBuilder();

            while ((bf = br.readLine()) != null) {
                System.out.println(bf);
                sb.append(bf);
            }
//            System.out.println(sb.toString());
//            String uri = getUri(sb.toString());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            try {
                String content = getFile("//index.html");
                bw.write("HTTP/1.1 200 OK\r\n");
                bw.write("Content-Type: text/html;charset=UTF-8\r\n\r\n");
                bw.write(content);
            } catch (IOException e) {
                e.printStackTrace();
                bw.write("HTTP/1.1 404 Not Found\r\n");
                bw.write("Content-Type: text/html;charset=UTF-8\r\n\r\n");
            }
            bw.flush();
            bw.close();
            br.close();
            socket.close();
        }
    }

    private String getUri(String firstLine) {
        int i = firstLine.indexOf(" ");
        int j = firstLine.indexOf(" ", i + 1);
        return firstLine.substring(i + 1, j);
    }

    private String getFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(WEBROOT + filePath)));
        try {
            StringBuilder sb = new StringBuilder();
            String buf = null;
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileNotFoundException();
        } finally {
            br.close();
        }
    }
}
