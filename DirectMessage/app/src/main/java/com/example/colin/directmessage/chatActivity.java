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
        if (toBedisplayed.length() > 2) {
            runOnUiThread(new Runnable() {
                public void run() {
                    textView.append("FRIEND:" + toBedisplayed + "\n");
                }
            });
        }else {
            runOnUiThread(new Runnable() {
                public void run() {
                    textView.append("FRIEND:" + getName(toBedisplayed) + "\n");
                }
            });
        }
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

        if (player2.round > 24){
            if(player2.score > player1.score){
                textView.append("YOU WIN!!!!!!!!!.\n");
                out.println("YOU LOOSE!!!!!!!!!!\n");
            }
            if(player1.score < player2.score){
                textView.append("YOU LOOSE!!!!!!!!!!\n");
                out.println("YOU WIN!!!!!!!!!\n");
            } else{
                textView.append("GAME OVER, TIE\n");
                out.println("GAME OVER, TIE\n");
            }
        }

        textView.append("clientCard: " + Integer.valueOf(clientCard) + "serverValue: " + Integer.valueOf(serverValue) + "\n");
        if (Integer.valueOf(clientCard) > Integer.valueOf(serverValue)){
            player2.score++;
            textView.append("You lost the round.\n");
            out.println("You won the round");
        }else{
            player2.score--;
            textView.append("You won the round.\n");
            out.println("You lost the round");
        }
        player2.round++;

    }

    public void respondFunction(View view) throws IOException {
        textView = (TextView) findViewById(R.id.textView2);

        passingTest = String.valueOf(player2.cardValue.get(0));
        player2.cardValue.remove(0);

        textView.append("ME: "+ getName(passingTest) + "\n");

        PrintWriter out = null;
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(passingTest);

        if (IP == null){
            submitServer(passingTest);
        }

    }

    public void passObject(player player) {
        player2 = player;
    }

    public String getName(String card) {

        String suit;
        String Value;

        switch ((Integer.valueOf(card) % 4)) {
            case 0:
                suit = "Clubs";
                break;
            case 1:
                suit = "Hearts";
                break;
            case 2:
                suit = "Diamonds";
                break;
            case 3:
                suit = "Spades";
                break;
            default: suit = "";
        }

        switch ((Integer.valueOf(card) % 13)) {
            case 0:
                Value = "Two";
                break;
            case 1:
                Value = "Three";
                break;
            case 2:
                Value = "Four";
                break;
            case 3:
                Value = "Five";
                break;
            case 4:
                Value = "Six";
                break;
            case 5:
                Value = "Seven";
                break;
            case 6:
                Value = "Eight";
                break;
            case 7:
                Value = "Nine";
                break;
            case 8:
                Value = "Ten";
                break;
            case 9:
                Value = "Jack";
                break;
            case 10:
                Value = "Queen";
                break;
            case 11:
                Value = "King";
                break;
            case 12:
                Value = "Ace";
                break;
            default:Value = "";
        }
        return Value + " of " + suit;
    }
}
