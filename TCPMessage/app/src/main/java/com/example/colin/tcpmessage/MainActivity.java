package com.example.colin.tcpmessage;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView error;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void clientMethod(View view) {
        if (isOnline()){
            openEditorForChat(view);
        }
    }

    public void openEditorForChat(View view) {

        Intent intent = new Intent(this, chatActivity.class);
        intent.putExtra("FriendIP", "10.0.0.4");
        intent.putExtra("FriendSock", 3490);
        startActivityForResult(intent, 1001);
    }
}
