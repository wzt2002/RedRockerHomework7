package com.example.redrockerhomework7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class lv4Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "myLog";
    Handler myHandlder = new MyHandlder();
    CheckBox checkBox;
    EditText password;
    EditText userName;

    private class MyHandlder extends Handler{
        Boolean checkInfo=false;
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String str = msg.obj.toString();
            JSONObject jsonObject = null;
            String errorCode = null;
            try {
                jsonObject = new JSONObject(str);
                errorCode = jsonObject.getString("errorCode");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(errorCode.equals("-1"))
                checkInfo=false;
            else
                checkInfo=true;

            if(checkInfo==false)
                Toast.makeText(getApplicationContext(),"账号密码不匹配!", LENGTH_SHORT).show();
            else{
                Intent intent = new Intent(getApplicationContext(), lv2and3Activity.class);
                startActivity(intent);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv4);
        Button loginBtn = (Button) findViewById(R.id.login);
        Button regBtn = (Button) findViewById(R.id.register);
        checkBox = findViewById(R.id.checkBox);
        userName = (EditText) findViewById(R.id.UserName);
        password = (EditText) findViewById(R.id.Password);
        loginBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if (!checkBox.isChecked())
                Toast.makeText(getApplicationContext(),"请先同意用户协议!", LENGTH_SHORT).show();
            case R.id.login:
                if (!checkBox.isChecked())
                    Toast.makeText(getApplicationContext(),"请先同意用户协议!", LENGTH_SHORT).show();
                else {
                    checkInfo();

                }
                break;
        }
    }

    private void checkInfo() {
        new Thread(new Runnable() {
            private OkHttpClient client;
            Response response=null;
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("username",userName.getText().toString())
                        .add("password",password.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url("https://www.wanandroid.com/user/login")
                        .post(requestBody)
                        .build();
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                try {
                    message.obj = response.body().string();
                    myHandlder.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}



