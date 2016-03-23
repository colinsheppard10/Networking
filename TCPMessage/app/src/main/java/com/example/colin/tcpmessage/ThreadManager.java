package com.example.colin.tcpmessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Colin on 2/22/16.
 */
public class ThreadManager implements Runnable {

    public Thread t;
    public chatActivity ChatActivity;
    public ThreadManager(chatActivity newChatAcitivity){
        ChatActivity = newChatAcitivity;
    }

    @Override
    public void run() {

        String message;

        TCPManager client = new TCPManager();
        Socket socket = client.SetUpClient("10.0.0.4", 3490);

        message = "1Hello From Android";
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(message);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String Response = reader.readLine();
            ChatActivity.displayString(Response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // This is here to test
        // I dont think this is getting exicuted 
        // how do I get back to this thread after calling "ChatActivity.displayString(Response)"
        message = "Second hello";
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(message);
        /*
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public void start(){
        if (t == null){
            t = new Thread(this);
            t.start();
        }
    }
}
/*

 */
