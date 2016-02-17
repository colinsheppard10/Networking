package com.example.colin.mytcp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    Button myButton;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button) findViewById(R.id.button);
        output = (TextView) findViewById(R.id.textView);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    updateDisplay("button clicked, requesting data");
                    requestData("10.107.210.43");
                } else {
                    updateDisplay("Network is not available");
                }
            }
        });
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
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

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String content = null;

            try {
                content = TCPManager.GetDate(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            updateDisplay(result);
        }
    }

    public void updateDisplay(String message) {output.append(message + "\n");
    }

}
