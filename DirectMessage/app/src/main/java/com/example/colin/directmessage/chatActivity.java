package com.example.colin.directmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

public class chatActivity extends AppCompatActivity{

    TextView textView;
    String toBedisplayed;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_page);

        textView = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        String IP = intent.getStringExtra("IP");
        int socket = intent.getIntExtra("socket", -1);

        ThreadManager myThread = new ThreadManager(this,"0000", 1337, "Hello from Android");
        myThread.start();
    }

    public void displayString(String response){
        toBedisplayed = response;
        runOnUiThread(new Runnable() {
            public void run() {
                textView.append(toBedisplayed);
            }
        });
    }
}
