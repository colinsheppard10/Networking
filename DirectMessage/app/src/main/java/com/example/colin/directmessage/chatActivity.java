package com.example.colin.directmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
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
    player player1;
    player player2;
    gameManager newGame;
    String clientCard;
    String IP;
    ScrollView scroll;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_page);
        textView = (TextView) findViewById(R.id.textView2);
        scroll = (ScrollView) findViewById(R.id.scrollView);

        Intent intent = getIntent();
        IP = intent.getStringExtra("IP");
        int socketNumber = intent.getIntExtra("socket", -1);
        ThreadManager myThread;
        player1 = new player();
        player2 = new player();

        if (IP == null) {
           newGame = new gameManager(player1, player2);
            myThread = new ThreadManager(this, IP, socketNumber, player1);
        }else{
            myThread = new ThreadManager(this, IP, socketNumber, null);
        }

        myThread.start();
    }

    public void displayString(String response){
        toBedisplayed = response;
        runOnUiThread(new Runnable() {
            public void run() {
                textView.append("FRIEND:"+ toBedisplayed + "\n");
            }
        });
    }

    public void passToMain(Socket passedSocket){
        socket = passedSocket;
    }

    public void submitClient(String clientValue){
        clientCard = clientValue;
    }

    public void submitServer(String serverValue) throws IOException {
        textView = (TextView) findViewById(R.id.textView2);
        PrintWriter out = null;
        out = new PrintWriter(socket.getOutputStream(), true);

        if (Integer.valueOf(clientCard) > Integer.valueOf(serverValue)){
            textView.append("You lost the round.\n");
            out.println("You won the round");
        }else{
            textView.append("You won the round.\n");
            out.println("You lost the round");
        }
    }

    public void respondFunction(View view) throws IOException {
        textView = (TextView) findViewById(R.id.textView2);

        passingTest = String.valueOf(player2.cardValue.get(0));
        player2.cardValue.remove(0);

        textView.append("ME: "+ passingTest + "\n");

        PrintWriter out = null;
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(passingTest);

        if (IP == null){
            submitServer(String.valueOf(player2.cardValue.get(0)));
        }

    }

    public void passObject(player player) {
        player2 = player;
    }
}
