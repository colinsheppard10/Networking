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
    public void run(){
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line;


        TCPManager client = new TCPManager();
        Socket socket = client.SetUpClient("10.0.0.4", 3490);

        while(true) {
            PrintWriter out = null;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("1Hello From android");
            ChatActivity.displayString("HELLOW");
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n" );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = sb.toString();
            ChatActivity.displayString(message);

            if (sb.toString() == "quit") {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
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
