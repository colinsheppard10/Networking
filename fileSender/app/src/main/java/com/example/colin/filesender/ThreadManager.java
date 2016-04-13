package com.example.colin.filesender;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.MessageFormat;
import android.content.Context;


/**
 * Created by Colin on 4/6/16.
 */
public class ThreadManager extends Thread {
    String fileName;
    int partitions;
    int size;
    MainActivity myMainActivity;

    ThreadManager(MainActivity passedMainActivity, String passedFileName, int passedPartitions, int passedSize){
        fileName = passedFileName;
        partitions = passedPartitions;
        myMainActivity = passedMainActivity;
        size = passedSize;
    }
    public Thread t;

    public void run(){
        Socket socket = null;
        String Response = null;
        PrintWriter out = null;

        try {
            socket = TCPManager.setUpClient(1337, "10.0.0.13");

            out = new PrintWriter(socket.getOutputStream(), true);
            for(int i = 0; i <= 5 ; i++) {
                out.println("hi");
                Thread.sleep(3000);
            }
            /*
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Response = reader.readLine();
            myMainActivity.setToBedisplayed(Response);
            */


            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
