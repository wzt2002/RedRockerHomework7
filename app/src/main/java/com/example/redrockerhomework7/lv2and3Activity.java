package com.example.redrockerhomework7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class lv2and3Activity extends AppCompatActivity {
    Handler myHandlder = new MyHandlder();
    RecyclerView myrecyclerview;
    List<String> titlelist = new ArrayList<String>();
    List<String> shareUserlist = new ArrayList<String>();
    List<String> linklist = new ArrayList<String>();

    private class MyHandlder extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String str = msg.obj.toString();
            JSONArray datas = null;
            try {
                JSONObject jsonObject = new JSONObject(str);
                JSONObject data = jsonObject.getJSONObject("data");
                datas = data.getJSONArray("datas");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < datas.length(); i++) {
                try {
                    JSONObject jsonObject = datas.getJSONObject(i);
                    titlelist.add(jsonObject.getString("title"));
                    shareUserlist.add(jsonObject.getString("shareUser"));
                    linklist.add(jsonObject.getString("link"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            myAdapter myadapter = new myAdapter(titlelist, shareUserlist, linklist);
            myrecyclerview.setAdapter(myadapter);
            myadapter.setOnURLClickListener(new myAdapter.OnURLClickListener() {
                @Override
                public void OnClick(String URL) {
                    Intent intent = new Intent(getApplicationContext(), newURLActivity.class);
                    intent.putExtra("url", URL);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv2and3);
        myrecyclerview = (RecyclerView) findViewById(R.id.myrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myrecyclerview.setLayoutManager(linearLayoutManager);
        initData();
    }


    private void initData() {
        new Thread(new Runnable() {
            private OkHttpClient client;

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://www.wanandroid.com/article/list/0/json")
                        .build();
                Response response = null;
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

