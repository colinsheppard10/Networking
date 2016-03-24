package com.example.colin.directmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Colin on 3/22/16.
 */
public class ThreadManager extends Thread {

    public Thread t;
    public String IP;
    public int SocketNumber;
    public String message;
    public chatActivity ChatActivity;

    ThreadManager(chatActivity passedChatActivity ,String passedIP, int passedSocket, String passedMessage){
        IP = passedIP;
        SocketNumber = passedSocket;
        message = passedMessage;
        ChatActivity = passedChatActivity;
    }

    public void run(){
        Socket socket = null;
            try{
                if (IP == "0000") {
                    socket = TCPManager.setUpServer(SocketNumber);
                }else{
                    socket = TCPManager.setUpClient(SocketNumber, IP);
                }
                PrintWriter out = null;
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
                ChatActivity.passToMain(socket,"from run");

                String Response = null;
                while (Response != "close") {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Response = reader.readLine();
                    ChatActivity.displayString(Response);
                }
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void start(){
        if (t == null){
            t = new Thread(this);
            t.start();
        }
    }
}
