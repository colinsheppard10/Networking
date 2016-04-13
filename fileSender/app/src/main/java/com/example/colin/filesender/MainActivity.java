package com.example.colin.filesender;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {

    public Button oneMB;
    public Button twoMB;
    public Button fourMB;
    public Button eightMB;
    public TextView testText;
    public String toBedisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        oneMB = (Button) findViewById(R.id.button);
        twoMB = (Button) findViewById(R.id.button2);
        fourMB = (Button) findViewById(R.id.button3);
        eightMB = (Button) findViewById(R.id.button4);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = (TextView) findViewById(R.id.textView);
        //testText.setText(String.valueOf(SystemClock.elapsedRealtime()));
    }

    public void sendOne(View v) throws IOException, InterruptedException {
            readFunction("oneMB.txt", 8, 1);
    }

    public void sendTwo(View v) throws IOException {
        readFunction("twoMB.txt", 4, 2);
    }

    public void sendFour(View v) throws IOException {
        readFunction("fourMB.txt", 2, 4);

    }

    public void sendEight(View v) throws IOException {
        readFunction("eightMB.txt", 1, 8);

    }

    public void readFunction(String fileName, int partitions, int size) throws IOException {

        File externalDirectory = getExternalFilesDir(null);
        String path = externalDirectory.getPath();
        path += "/" + fileName;

        ThreadManager newThread = new ThreadManager(this, path, partitions, size);
        newThread.start();

    }

    public void setUpServer(View view) throws InterruptedException {

        File externalDirectory = getExternalFilesDir(null);
        String path = externalDirectory.getPath();

        serverThreadManager newServer = new serverThreadManager(this, path);
        newServer.start();
    }

    public void setToBedisplayed(String passedToBedisplayed){
        toBedisplayed = passedToBedisplayed;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                testText.append(toBedisplayed);
            }
        });
    }
}
