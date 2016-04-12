package com.example.colin.directmessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public Button waitButton;
    public Button setButton;
    public EditText friendSocket;
    public EditText mySocket;
    public EditText myIP1;
    public EditText myIP2;
    Editable friendSocketString;
    public TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waitButton = (Button) findViewById(R.id.button);
        setButton = (Button) findViewById(R.id.button2);
        testText = (TextView) findViewById(R.id.textView);

    }

    public void waitFunction(View view){
        friendSocket = (EditText) findViewById(R.id.editText);
        int friendSocketNumber = Integer.valueOf(String.valueOf(friendSocket.getText()));
        friendSocketString = friendSocket.getText();

        openEditorForChat(view, null, friendSocketNumber);
    }

    public void setupFunction(View view){

        mySocket = (EditText) findViewById(R.id.editText4);
        myIP1 = (EditText) findViewById(R.id.editText2);
        myIP2 = (EditText) findViewById(R.id.editText3);
        int mySocketNumber = Integer.valueOf(String.valueOf(mySocket.getText()));
        String myIP = myIP1.getText() + "." + myIP2.getText();

        openEditorForChat(view, myIP, mySocketNumber);
    }

    public void openEditorForChat(View view, String IP, int socket ){
        Intent intent = new Intent(this, chatActivity.class);
        intent.putExtra("socket", socket);
        intent.putExtra("IP", IP);
        startActivityForResult(intent,1001);
    }
}
