package com.example.redrockerhomework7;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    WebView webview;
    List<String> titlelist=new ArrayList<String>();
    List<String> shareUserlist=new ArrayList<String>();
    List<String> linklist=new ArrayList<String>();
    OnURLClickListener onURLClickListener;

    public myAdapter(List<String> titlelist, List<String> shareUserlist, List<String> linklist) {
        this.titlelist = titlelist;
        this.shareUserlist = shareUserlist;
        this.linklist = linklist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView shareUser;
        TextView link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            shareUser=itemView.findViewById(R.id.shareUser);
            link=itemView.findViewById(R.id.link);
            webview = itemView.findViewById(R.id.webview);
        }
    }

    @NonNull
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.ViewHolder holder, int position) {
        holder.title.setText(titlelist.get(position));
        holder.shareUser.setText(shareUserlist.get(position));
        holder.link.setText(linklist.get(position));
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onURLClickListener.OnClick(linklist.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return titlelist.size();
    }

    public void setOnURLClickListener(OnURLClickListener onURLClickListener){
        this.onURLClickListener=onURLClickListener;
    }
    public interface OnURLClickListener {
        public void OnClick(String URL);
    }

}
