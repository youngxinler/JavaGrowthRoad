package com.server.multiBlockHttpServer;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket s;

    public ConnectionHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted() && !s.isClosed()) {
            try {
                BufferedReader br = getBufReader(s);
                BufferedWriter bw = getBufWriter(s);

                String buf;
                StringBuilder sb = new StringBuilder();
                while (br.ready() && (buf = br.readLine()) != null) {
                    System.out.println(buf);
                    sb.append(buf);
                }
                bw.write("HTTP/1.1 200 OK\r\n");
                bw.write("Connection: close\r\n");
                bw.write("Content-Type: text/html;charset=UTF-8\r\n\r\n");

                bw.write("<html><head><title>blast!</title><head> <body>welcome to blast!</body>");
                bw.flush();
                bw.close();
                br.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedReader getBufReader(Socket s) throws IOException {
        return new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    private BufferedWriter getBufWriter(Socket s) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }
}
