package com.example.redrockerhomework7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            txt.setText(Integer.toString((Integer.parseInt(txt.getText().toString())-1)));
            if (Integer.parseInt(txt.getText().toString())>0)
                handler.postAtTime(runnable, SystemClock.uptimeMillis() +1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postAtTime(runnable, SystemClock.uptimeMillis() +1000);
            }
        });
    }
}