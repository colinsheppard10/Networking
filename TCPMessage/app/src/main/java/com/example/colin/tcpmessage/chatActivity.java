package com.example.colin.tcpmessage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

public class chatActivity extends AppCompatActivity {

    String toBedisplayed;
    TextView textView;

   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.chat_page);
       textView = (TextView) findViewById(R.id.textView2);

       Intent intent = getIntent();

       String FriendIP = intent.getStringExtra("FriendIP");
       int FriendSocket = intent.getIntExtra("FriendSock", -1);
       textView.append(FriendIP + "\n" + FriendSocket);

       ThreadManager myThread = new ThreadManager(this);
       myThread.start();
   }

    public void displayString(String s){
        toBedisplayed = s;
        runOnUiThread(new Runnable() {
            public void run() {
                textView.setText(toBedisplayed);
            }
        });
    }

}
