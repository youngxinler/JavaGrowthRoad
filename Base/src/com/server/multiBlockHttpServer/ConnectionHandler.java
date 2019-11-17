package com.server.multiBlockHttpServer;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    static String webRoot;
    private Socket s;

    public static final String RIGHT_HEADER = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=UTF-8\r\nConnection: close\r\n\r\n";
    public static final String ERROR_HEADER = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html;charset=UTF-8\r\nConnection: Close\r\n\r\n";
    public static final String NOTFOUND_CONTENT = "<html><head><title>Blast</title></head> <body> Sorry, 404 Not Found </body> </html>";

    public ConnectionHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (!s.isClosed()) {
            try {
                BufferedReader br = getBufReader(s);
                BufferedWriter bw = getBufWriter(s);

                String buf;
                StringBuilder sb = new StringBuilder();
                while (br.ready() && (buf = br.readLine()) != null) {
                    sb.append(buf);
                }

                System.out.println("req " + sb.toString());

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
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                new InputStreamReader(new FileInputStream(webRoot + filePath)))) {
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

    private BufferedReader getBufReader(Socket s) throws IOException {
        return new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    private BufferedWriter getBufWriter(Socket s) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }
}
