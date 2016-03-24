package com.example.colin.directmessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Colin on 3/22/16.
 */
public class TCPManager {
    public static Socket setUpServer(int userSocket) throws IOException {

        ServerSocket serverSocket = new ServerSocket(userSocket);
        Socket socket = serverSocket.accept();

        return socket;
    }

    public static Socket setUpClient(int userSocket, String userIP) throws IOException {

        Socket socket = new Socket(userIP, userSocket);
        return socket;


    }


}
