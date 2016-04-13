package com.example.colin.filesender;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Colin on 4/7/16.
 */
public class serverThreadManager extends Thread {
    MainActivity mainActivity;
    public Thread t;
    public File file;
    String path;

    serverThreadManager(MainActivity myMainActivity, String passedPath){
        mainActivity = myMainActivity;
        path = passedPath;
    }

    public void run() {
        Socket socket = null;
        String Response = null;
        PrintWriter out = null;

        try {
                socket = TCPManager.setUpServer(1337);
                for(int i = 0; i<= 5; i++){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Response = reader.readLine();
                     mainActivity.setToBedisplayed(Response);
                }
                socket.close();

                socket = TCPManager.setUpServer(1337);
                mainActivity.setToBedisplayed("second server set");
                for(int i = 0; i<= 4; i++){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Response = reader.readLine();
                    mainActivity.setToBedisplayed(Response);
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
