package com.example.redrockerhomework7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.FormBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);
        Button btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3=(Button)findViewById(R.id.button3);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                enterActivity(lv1Activity.class);
                break;
            case R.id.button2:
           enterActivity(lv2and3Activity.class);


                break;
            case R.id.button3:
                enterActivity(lv4Activity.class);
                break;
        }
    }
    public void enterActivity(Class cla){
        Intent intent = new Intent(this,cla);
        startActivity(intent);
    }
}