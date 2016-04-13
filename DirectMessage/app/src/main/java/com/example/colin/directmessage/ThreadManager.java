package com.example.colin.directmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by Colin on 3/22/16.
 */
public class ThreadManager extends Thread {

    public Thread t;
    public String IP;
    public int SocketNumber;
    public player player1;
    public chatActivity ChatActivity;

    ThreadManager(chatActivity passedChatActivity ,String passedIP, int passedSocket, player passedPlayer){
        IP = passedIP;
        SocketNumber = passedSocket;
        player1 = passedPlayer;
        ChatActivity = passedChatActivity;
    }

    public void run(){
        Socket socket = null;
            try{
                if (IP == null) {
                    socket = TCPManager.setUpServer(SocketNumber);
                    ObjectOutputStream out = null;
                    out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(player1);
                }else{
                    socket = TCPManager.setUpClient(SocketNumber, IP);
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    try {
                        ChatActivity.passObject((player)in.readObject());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                ChatActivity.passToMain(socket);

                String Response = null;
                while (Response != "close") {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Response = reader.readLine();

                    if (IP == null) {
                        String tester= "server passing: " + Response + " to submitClient\n";
                        ChatActivity.displayString(tester);
                        ChatActivity.submitClient(Response);
                    }
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
