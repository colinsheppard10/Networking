package com.example.colin.directmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class chatActivity extends AppCompatActivity{

    TextView textView;
    String toBedisplayed;
    Socket socket = null;
    String passingTest = null;
    EditText myMessage;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_page);

        textView = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        String IP = intent.getStringExtra("IP");
        int socketNumber = intent.getIntExtra("socket", -1);
        //textView.append(IP + "  " + socketNumber);

       ThreadManager myThread = new ThreadManager(this,"0000", socketNumber, "Hello from Android");
       myThread.start();
    }

    public void displayString(String response){
        toBedisplayed = response;
        runOnUiThread(new Runnable() {
            public void run() {
                textView.append("FRIEND: " + toBedisplayed + "\n");
            }
        });
    }

    public void passToMain(Socket passedSocket, String test){
        socket = passedSocket;
    }

    public void respondFunction(View view) throws IOException {
        textView = (TextView) findViewById(R.id.textView2);
        myMessage = (EditText) findViewById(R.id.editText5);

        passingTest = String.valueOf(myMessage.getText());
        textView.append("ME: "+ passingTest + "\n");


        PrintWriter out = null;
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("FRIEND: " + passingTest);
    }
}
