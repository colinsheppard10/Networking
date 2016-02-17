package com.example.colin.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPManager {
    public static String GetDate(String uri) throws IOException {

        ServerSocket serverSocket = new ServerSocket(3490);
        Socket socket = serverSocket.accept();

        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
