package com.example.colin.tcpmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPManager {
    public static String SetUpServer(int userSocket) throws IOException {

        ServerSocket serverSocket = new ServerSocket(userSocket);
        Socket socket = serverSocket.accept();

        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            sb.toString();

            return "conversation over";
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

    public static Socket SetUpClient(String uri, int userSocket) {
        Socket socket = null;

        try {
            socket = new Socket("10.0.0.4", 3490);
            return socket;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
